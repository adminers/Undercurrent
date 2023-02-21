package com.qiaweidata.un;

import com.qiaweidata.pojo.FolderInfo;
import com.qiaweidata.un.enums.LoopFloderEnum;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 测试
 *
 * @Title:
 * @Description:
 * @Company:www.qiaweidata.com
 * @author:shenshilong
 * @date:
 * @version:V1.0
 */
public class TestFileList {

    private Path rootPath;

    private int folderNum;

    private List<FolderInfo> folderInfos = new ArrayList<>(10);

    private String computerUser;

    private String parentId;

    private static StringBuilder logBuilder = new StringBuilder();

    private static final String property = System.getProperty("line.separator");

    /**
     * 过滤不要的文件夹名称
     */
    private static Set<String> FILTER_NAMES = new HashSet<>(2);

    static {
        FILTER_NAMES.add("$RECYCLE.BIN");
        FILTER_NAMES.add("SystemVolumeInformation");
    }

    public TestFileList() {
    }

    public TestFileList(Path rootPath, String parentId) {

        this.computerUser = System.getProperty("user.name");
        this.rootPath = rootPath;
        this.parentId = parentId;
    }

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        String path = "C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.56";        //要遍历的路径
        File file = new File(path);        //获取其file对象
        File[] fs = file.listFiles();    //遍历path下的文件和目录，放在File数组中
        LoopFloderEnum type = LoopFloderEnum.ONE_LEVEL_LIST;
        for (File f : fs) {
            String replaceName = f.getName().replace(" ", "");
            if (FILTER_NAMES.contains(replaceName)) {
                continue;
            }
            logBuilder.append(f).append(property);
            File[] files = f.listFiles();
            if (LoopFloderEnum.ALL_LIST.equals(type)) {
                sout(files);
            }
        }
        //sout(fs[0].listFiles());
        System.out.println("----shenshilong------" + (System.currentTimeMillis() - startTime) + " ms.");
        Path logPath = Paths.get("F:\\temp\\file.log");
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, StandardCharsets.UTF_8)) {

            writer.write(logBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*

        String p = null;
        try {
            p = new String("F:\\".getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(p.toString());
        Path dir = Paths.get(p);
        File[] fs = dir.toFile().listFiles();
        try {
            //for (int i = 0; i < 10_000; i++) {
                TimeUnit.MILLISECONDS.sleep(100);
            for (File f : fs) {
                new TestFileList(f.toPath(), "xxx").loop(f.toPath());
            }

            //}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Path logPath = Paths.get("F:\\temp\\file.log");
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, StandardCharsets.UTF_8)) {

            writer.write(logBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        */



        /*childAll("C:\\Users\\Administrator\\.m2");*/
    }

    public static void sout(File[] fs) {

        if (null == fs ||
            fs.length == 0) {
            return;
        }
        for (File f : fs) {
            logBuilder.append(f).append(System.getProperty("line.separator"));
            File[] files = f.listFiles();
            sout(files);
        }
    }

    public static void childAll(String path) {

        File file = new File(path);
        File[] childFiles = file.listFiles();
        if (null == childFiles) {
            return;
        }
        for (File childFile : childFiles) {
            System.out.println(childFile.getName() +
                ";最后修改时间:" + childFile.lastModified() + ";");
        }
    }

    public static void childFolder(String path) {

    }

    public static void childFile(String path) {

    }

    private void loop(Path dir) {

        try {
            Files.walkFileTree(dir,
                EnumSet.noneOf(FileVisitOption.class),
                Integer.MAX_VALUE,
                new FileVisitor());
            System.out.println(TestFileList.this.folderNum++);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class FileVisitor extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            //System.out.println(file.getFileName());
            //System.out.println(folderNum);
            /*try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            logBuilder.append(file).append(System.getProperty("line.separator"));
            /*FolderInfo folderInfo = new FolderInfo();
            folderInfo.setId(UUID.randomUUID().toString().replace("-", ""));
            folderInfo.setMachineId("");
            folderInfo.setFolderName(file.getFileName().toString());
            folderInfo.setFolderSort(String.valueOf(TestFileList.this.folderNum));
            folderInfo.setIconPath("a");
            folderInfo.setCreateTime(new Date(attrs.creationTime().toMillis()));
            folderInfo.setCreateUser(TestFileList.this.computerUser);
            folderInfo.setUpdateTime(new Date(attrs.lastModifiedTime().toMillis()));
            folderInfo.setUpdateUser(TestFileList.this.computerUser);
            folderInfo.setFileSize(attrs.size());
            TestFileList.this.folderInfos.add(folderInfo);*/
            TestFileList.this.folderNum++;
            return FileVisitResult.CONTINUE;
        }
    }
}
