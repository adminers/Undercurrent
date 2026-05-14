import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageCrawler {

    public static void main(String[] args) {
        // 1. 设置目标网址（替换为你需要爬取的页面）
        String targetUrl = "https://example.com/comments-page";
        // 2. 设置保存图片的文件夹
        String saveDir = "downloaded_images";
        // 3. 评论区图片的 CSS 选择器 (需根据实际网页修改)
        // 假设评论区的图片都在 class 为 'comment-content' 的 div 下的 img 标签里
        String imgSelector = ".comment-content img";

        try {
            crawlAndSaveImages(targetUrl, imgSelector, saveDir);
        } catch (IOException e) {
            System.err.println("爬取过程中出现错误: " + e.getMessage());
        }
    }

    public static void crawlAndSaveImages(String url, String selector, String saveDir) throws IOException {
        // 创建本地目录
        Path path = Paths.get(saveDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        System.out.println("正在连接到: " + url);
        // 使用 Jsoup 获取并解析网页 (模拟浏览器 User-Agent 以防被拦截)
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .get();

        // 提取所有图片标签
        Elements images = doc.select(selector);
        System.out.println("共找到 " + images.size() + " 张图片。");

        int count = 0;
        for (Element img : images) {
            // 获取图片绝对路径 (src 属性)
            String imgSrc = img.absUrl("src");
            
            // 处理一些网站使用懒加载，图片链接可能在 data-src 或 data-original 中
            if (imgSrc.isEmpty()) {
                imgSrc = img.absUrl("data-src");
            }

            if (!imgSrc.isEmpty()) {
                try {
                    downloadImage(imgSrc, path);
                    count++;
                    System.out.println("已下载: " + imgSrc);
                } catch (Exception e) {
                    System.err.println("下载失败: " + imgSrc + " (" + e.getMessage() + ")");
                }
            }
        }
        System.out.println("任务完成！成功保存了 " + count + " 张图片到 " + saveDir);
    }

    private static void downloadImage(String imageUrl, Path targetDir) throws IOException {
        URL url = new URL(imageUrl);
        // 使用 UUID 防止文件名冲突，从 URL 中简单提取后缀
        String fileName = UUID.randomUUID().toString();
        String extension = ".jpg"; // 默认为 jpg
        if (imageUrl.contains(".")) {
            extension = imageUrl.substring(imageUrl.lastIndexOf("."));
            // 过滤掉 URL 后的参数
            if (extension.contains("?")) {
                extension = extension.substring(0, extension.indexOf("?"));
            }
        }

        try (InputStream in = url.openStream()) {
            Files.copy(in, targetDir.resolve(fileName + extension), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}