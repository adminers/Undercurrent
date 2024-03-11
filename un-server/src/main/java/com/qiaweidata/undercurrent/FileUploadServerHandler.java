package com.qiaweidata.undercurrent;

import com.qiaweidata.pojo.FileUploadEntity;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @Description: 文件上传demo 服务端处理类
 * @Author: walking
 * @Date: $
 */

public class FileUploadServerHandler extends SimpleChannelInboundHandler<FileUploadEntity> {
    private int byteRead;//读取到的数据的长度
    private volatile int start = 0;//读取的起始位置
    private String file_dir = "D:";//服务器保存文件的路径
    private long startTime;//开始处理的时间
    private RandomAccessFile randomAccessFile;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        startTime = System.currentTimeMillis();
        System.out.println("channelActive");
    }

    /**
     * 获取客户端发送的数据
     *
     * @param ctx
     * @param fileUploadEntity
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FileUploadEntity fileUploadEntity) throws Exception {
        FileUploadEntity ef = fileUploadEntity;
        byte[] bytes = ef.getBytes();
        byteRead = ef.getDataLength();//dataLength 每次接收到的数据长度
        System.out.println("byteRead=>" + byteRead);
        String md5 = ef.getFileName();//文件名
        String path = file_dir + File.separator + md5;//文件路径
        randomAccessFile = new RandomAccessFile(path, "rw");
        randomAccessFile.seek(start);
        randomAccessFile.write(bytes);//写入数据
        randomAccessFile.close();
        //修改初始值 记录下次要从哪个位置读数据，并返回给客户端 告诉客户端下一次从文件的哪个位置开始传
        start = start + byteRead;
        if (byteRead > 0) {
            System.out.println("返回给客户端数据=》" + start);
            ctx.writeAndFlush(start);//写回客户端
        } else {
            System.out.println("接收文件耗时：" + (System.currentTimeMillis() - startTime));
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
