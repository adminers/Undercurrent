package com.qiaweidata.undercurrent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
 
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
 
/**
 * Created by wrs on 2019/6/3,10:04
 * projectName: Testz
 * packageName: comtest.example.admin.netty.client
 */
public class NettyClient {
    private String host;
    private int port;
    private Channel channel;
    private Bootstrap b = null;
 
    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
        init();
    }
 
    private void init() {
        b = new Bootstrap();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        b.group(workerGroup).option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,
                                Unpooled.copiedBuffer(System.getProperty("line.separator").getBytes())));
                        //字符串编码解码
                        pipeline.addLast("decoder", new StringDecoder());
                        pipeline.addLast("encoder", new StringEncoder());
                        //心跳检测
                        pipeline.addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
                        //客户端的逻辑
                        pipeline.addLast("handler", new NettyClientHandler(NettyClient.this));
 
                    }
                });
    }
 
    public void start() {
        ChannelFuture f = b.connect(host, port);
        //断线重连
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (!channelFuture.isSuccess()) {
                    final EventLoop loop = channelFuture.channel().eventLoop();
                    loop.schedule(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("not connect service");
                            start();
                        }
                    }, 1L, TimeUnit.SECONDS);
                } else {
                    channel = channelFuture.channel();
                    System.out.println("connected");

//                    uploadFile("E:\\sd-webui-aki\\sd-webui-aki-v4\\outputs\\extras-images\\00005.png");
                }
            }
        });
    }

    public void uploadFile(String filePath) {

        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.err.println("File does not exist or is not a regular file: " + filePath);
            return;
        }

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] fileBytes = new byte[(int) file.length()];
            fis.read(fileBytes);

            ByteBuf buf = channel.alloc().buffer();
            buf.writeBytes(fileBytes);
            channel.writeAndFlush(buf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Channel getChannel() {
        return channel;
    }
 
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient("127.0.0.1", 6667);
        nettyClient.start();
    }
}
