package com.qiaweidata.undercurrent.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author: ReWinD00
 * @date: 2021-07-05 10:43
 */
@Component
@Scope("prototype")
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private int idleCounter = 0;

    public static Map<String, ChannelHandlerContext> ctxMap = new ConcurrentHashMap<>(16);

    /**
     * 业务数据处理
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            idleCounter = 0;
            ByteBuf in = (ByteBuf) msg;
            int readableBytes = in.readableBytes();
            byte[] bytes = new byte[readableBytes];
            in.readBytes(bytes);
            String msgStr = new String(bytes, StandardCharsets.UTF_8);
            System.out.println("服务端接受的消息 : " + msgStr);
            //System.out.print(in.toString(CharsetUtil.UTF_8));
//            JSONObject jsonObject = JSON.parseObject(msgStr);
//            if (jsonObject.getString("data").equals("up")) {
//                System.out.println("设备{}上线了", jsonObject.getInteger("id"));
//                ChannelHandlerContextHolder.updateMap(jsonObject.getInteger("id"), ctx);
//            } else {
//                System.out.println("设备上送信息{}", jsonObject.getString("data"));
//            }
            if ("shutdown".equals(msgStr)) {
                ctx.writeAndFlush(Unpooled.copiedBuffer("shutdown\r\n".getBytes()));
            } else {
                ctx.writeAndFlush(Unpooled.copiedBuffer("success\r\n".getBytes()));
            }
//            Thread.sleep(100);

        } finally {
            // 抛弃收到的数据
            ReferenceCountUtil.release(msg);
        }

    }

    /**
     * 从客户端收到新的数据、读取完成---调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("从客户端收到新的数据读取完成********");
        if (ctx != null) {
            String keyid = ctx.channel().id().asLongText();
            sendMessage(keyid, "服务器自动回复");
            ctx.flush();
        }
    }

    /**
     * 客户端与服务端建立连接--执行
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ctx.channel().read();
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        //此处不能使用ctx.close()，否则客户端始终无法与服务端建立连接
        System.out.println("客户端与服务端建立连接:{}" + clientIp);
        ctxMap.put(ctx.channel().id().asLongText(), ctx);
    }


    /**
     * 客户端与服务端断连-调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        //断开连接时，服务端关闭连接，避免造成资源浪费
        ctx.close();
        System.out.println("客户端与服务端断连:{}" + clientIp);
        ctxMap.remove(ctx.channel().id().asLongText());
    }


    /**
     * 当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (ctx != null) {
            ChannelHandlerContextHolder.removeByCtx(ctx);
            //抛出异常，断开与客户端的连接
            ctx.close();
            System.err.println("连接异常，服务端主动断开连接{}" + cause.getMessage());
        }

    }

    /**
     * 服务端read超时-调用
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        String clientIp = this.getClientIp(ctx);

        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state().equals(IdleState.WRITER_IDLE)) {
                System.out.println("客户端写超时:{}" + clientIp);
                idleCounter++;
                if (idleCounter > 3) {
                    System.out.println("客户端写超时超过3次，断开");
                    ctx.disconnect();
                }
            }
        }
    }

    public void sendMessage(String keyid, String msg){
        ChannelHandlerContext ctx = ctxMap.get(keyid);
        //ctx.write("test");
        ctx.writeAndFlush(Unpooled.copiedBuffer((msg + "\r\n").getBytes()));
    }

    public void sendMessage(String msg){
        ctxMap.forEach((id, ctx) -> {
            ctx.writeAndFlush(Unpooled.copiedBuffer((msg + "\r\n").getBytes()));
            ctx.flush();
        });

    }

    private String getClientIp(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        return clientIP;
    }
}
