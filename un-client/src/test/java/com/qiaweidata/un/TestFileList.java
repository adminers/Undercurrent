package com.qiaweidata.un;

import com.google.gson.Gson;
import com.qiaweidata.pojo.FolderInfo;
import com.qiaweidata.un.enums.FileTypeEnum;
import com.qiaweidata.un.enums.LoopFloderEnum;
import com.qiaweidata.un.pojo.FileInfo;
import com.qiaweidata.un.utils.DateUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    private int rootPathLength;

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

        /*String path = "C:\\Users\\Administrator\\Desktop\\apache-tomcat-8.5.56";        //要遍历的路径
        childPath(path, LoopFloderEnum.ONE_LEVEL_LIST);*/

        String path = "D:\\AI\\lama-cleaner-win\\lama-cleaner";        //要遍历的路径
        childPath(path, LoopFloderEnum.ALL_LIST);


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

    private static void childPath(String path, LoopFloderEnum type) {

        Objects.requireNonNull(type, "不能为空！");
        long startTime = System.currentTimeMillis();
        File parentFile = new File(path);
        File[] fs = parentFile.listFiles();
        TestFileList testFileList = new TestFileList();
        testFileList.rootPathLength = path.length();
        List<FileInfo> fileInfos = new ArrayList<>();
        testFileList.appendFile(fs, type, fileInfos);
        //logBuilder.append(new Gson().toJson(fileInfos));
        System.out.println("----shenshilong------" + (System.currentTimeMillis() - startTime) + " ms.");
        Path logPath = Paths.get("F:\\temp\\file.log");
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, StandardCharsets.UTF_8)) {
            writer.write(logBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<FileInfo> appendFile(File[] fs, LoopFloderEnum type, List<FileInfo> fileInfos) {

        if (null == fs ||
            fs.length == 0) {
            return Collections.emptyList();
        }

        for (File file : fs) {
            String replaceName = file.getName().replace(" ", "");
            if (FILTER_NAMES.contains(replaceName)) {
                continue;
            }
            String absolutePath = file.getAbsolutePath();
            logBuilder.append(absolutePath.substring(38)).append(property);
            if (LoopFloderEnum.ALL_LIST.equals(type)) {
                File[] files = file.listFiles();
                appendFile(files, type, fileInfos);
            }
            fileType(file, fileInfos);
        }
        return fileInfos;
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
            System.out.println(childFile.getName() + ";最后修改时间:" + childFile.lastModified() + ";");
        }
    }

    public void fileType(File file, List<FileInfo> fileInfos) {

        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(file.getName());
        fileInfo.setLastModified(file.lastModified());
        fileInfo.setFormatLastModified(DateUtil.formatLongTime(file.lastModified()));
        String path = file.getPath();
        if (file.isDirectory()) {
            fileInfo.setFileType(FileTypeEnum.DIRECTORY);
            fileInfo.setSuffix("DEFALUT_FOLDER");
        } else {
            fileInfo.setFileType(FileTypeEnum.FILE);
            int lastIndex = path.lastIndexOf('.');
            if (lastIndex != -1 &&
                lastIndex > this.rootPathLength) {
                fileInfo.setSuffix(path.substring(lastIndex + 1));
            }
        }
        fileInfo.setAbsolutePath(path);
        fileInfos.add(fileInfo);
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
