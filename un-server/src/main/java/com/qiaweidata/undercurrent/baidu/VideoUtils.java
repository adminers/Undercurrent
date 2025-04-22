//package com.qiaweidata.undercurrent.baidu;
//
//import com.xuggle.mediatool.IMediaWriter;
//import com.xuggle.mediatool.ToolFactory;
//import com.xuggle.xuggler.ICodec;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileFilter;
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Title: VideoUtils
// * @Description: VideoUtils
// * @date: 2023-10-28
// * @version: V1.0
// */
//public class VideoUtils {
//
//    public static void main(String[] args) throws IOException {
//        String imageFolderPath = "E:\\temp\\love_file\\2023-10\\";  // 图片文件夹路径
//        String outputVideoPath = "E:\\temp\\love_file\\video.mp4";  // 输出视频文件路径
//        int frameRate = 1;  // 视频帧率
//
//        // 创建一个IMediaWriter对象，用于将图像序列写入视频文件
//        IMediaWriter mediaWriter = ToolFactory.makeWriter(outputVideoPath);
//        int w = 1920;
//        int h = 1080;
//        long totalDuration = 0;  // 视频的总时长
//
//        // 设置视频编码器（H.264）
//        mediaWriter.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, w, h);
//
//        // 获取图片文件夹中的所有图片文件
//        File folder = new File(imageFolderPath);
//        File[] imageFiles = folder.listFiles(new FileFilter() {
//            @Override
//            public boolean accept(File file) {
//                String fileName = file.getName().toLowerCase();
//                return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".png");
//            }
//        });
//
//        // 根据帧率计算每一帧的时间间隔
//        long frameDuration = Math.round((1.0 / frameRate) * 1000000);  // 微秒
//
//        // 逐个读取图片文件并将其写入视频文件
//        for (int i = 0; i < imageFiles.length; i++) {
//            File imageFile = imageFiles[i];
//            BufferedImage image = ImageIO.read(imageFile);
//            BufferedImage videoImage = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
//            videoImage.getGraphics().drawImage(image, 0, 0, w, h, null);
//
//            // 将图像写入视频
//            mediaWriter.encodeVideo(0, videoImage, i * 1000000, TimeUnit.MICROSECONDS);
//        }
//
//        // 关闭视频文件
//        mediaWriter.close();
//
//        System.out.println("视频合成完成！");
//    }
//}
