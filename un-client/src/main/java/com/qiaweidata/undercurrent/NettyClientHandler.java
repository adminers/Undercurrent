package com.qiaweidata.undercurrent;

import com.google.gson.Gson;
import com.qiaweidata.pojo.FolderInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
 
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
 
/**
 * Created by wrs on 2019/6/3,10:03
 * projectName: Testz
 * packageName: comtest.example.admin.netty.client
 */
public class NettyClientHandler extends SimpleChannelInboundHandler{
    private NettyClient nettyClient;
    private String tenantId;
    private int attempts = 0;
 
    public NettyClientHandler(NettyClient nettyClient){
        this.nettyClient = nettyClient;
    }
 
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        System.out.println("service send message"+o.toString());
    }
 
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("output connected!");
        attempts = 0;
    }
 
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("offline");
        //使用过程中断线重连
        final EventLoop eventLoop = ctx.channel().eventLoop();
        if (attempts<12){
            attempts++;
        }
        int timeout = 2<<attempts;
        eventLoop.schedule(new Runnable() {
            @Override
            public void run() {
                nettyClient.start();
            }
        },timeout, TimeUnit.SECONDS);
        ctx.fireChannelInactive();
    }
 
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state().equals(IdleState.READER_IDLE)){
                System.out.println("READER_IDLE");
            }else if (event.state().equals(IdleState.WRITER_IDLE)){
                //发送心跳，保持长连接
                //String s = "NettyClient"+System.getProperty("line.separator");
                List<FolderInfo> folderInfos = new ArrayList<>(1);
                FolderInfo folderInfo = new FolderInfo();
                folderInfo.setId(UUID.randomUUID().toString().replace("-", ""));
                folderInfo.setMachineId("");
                folderInfos.add(folderInfo);
                String s = new Gson().toJson(folderInfos);
                ctx.channel().writeAndFlush("...");  //发送心跳成功
            }else if (event.state().equals(IdleState.ALL_IDLE)){
                System.out.println("ALL_IDLE");
            }
        }
        super.userEventTriggered(ctx,evt);
    }

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
            //System.out.println("服务端返回的消息 : " + msg.toString());
            System.out.println(msg.toString());

        } finally {
            // 抛弃收到的数据
            ReferenceCountUtil.release(msg);
        }

    }

    /**
     * 从服务端收到新的数据、读取完成---调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("从服务端收到新的数据读取完成********");
        if (ctx != null) {
            String keyid = ctx.channel().id().asLongText();
            ctx.flush();
        }
    }
}
