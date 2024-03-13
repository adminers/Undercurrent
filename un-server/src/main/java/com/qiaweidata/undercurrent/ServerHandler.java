package com.qiaweidata.undercurrent;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private FileOutputStream fos;
    private String fileName;
    private long fileSize;
    private long receivedSize;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client connected: " + ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if (msg instanceof ByteBuf) {
                ByteBuf byteBuf = (ByteBuf) msg;
                if (fos == null) {
                    // 获取文件名和文件大小
                    String fileMessage = byteBuf.toString(io.netty.util.CharsetUtil.UTF_8);
                    String[] parts = fileMessage.split(",");
//                    fileName = parts[0];
                    fileName = UUID.randomUUID().toString();
//                    fileSize = Long.parseLong(parts[1]);

                    // 创建文件输出流
                    fos = new FileOutputStream("E:\\giteeWork\\Undercurrent\\" + fileName + ".png");
                } else {
                    // 写入文件内容
                    while (byteBuf.isReadable()) {
                        byte b = byteBuf.readByte();
                        fos.write(b);
                        receivedSize++;
                    }

                    // 判断是否接收完整文件
//                    if (receivedSize >= fileSize) {
                        fos.close();
                        fos = null;
                        System.out.println("File received and saved: " + fileName);
//                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        new FileOutputStream(" E:\\giteeWork\\Undercurrent\\");

    }
}