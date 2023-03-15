package com.qiaweidata.un;

import cn.hutool.core.util.IdUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.qiaweidata.pojo.FolderInfo;
import com.qiaweidata.un.enums.FileTypeEnum;
import com.qiaweidata.un.enums.LoopFloderEnum;
import com.qiaweidata.un.h2.StoreSystemFile;
import com.qiaweidata.un.pojo.FileInfo;
import com.qiaweidata.un.utils.DateUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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

    private int rootPathLength;

    private long startTime;
    private long endTime;

    private StoreSystemFile storeSystemFile;

    /**
     * 过滤不要的文件夹名称
     */
    private static Set<String> FILTER_NAMES = new HashSet<>(2);

    static {
        FILTER_NAMES.add("$RECYCLE.BIN");
        FILTER_NAMES.add("SystemVolumeInformation");
    }

    public TestFileList() {
        this.storeSystemFile = new StoreSystemFile();
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
        //childPath(path, LoopFloderEnum.ALL_LIST);

        new TestFileList().childPath(path, LoopFloderEnum.ALL_LIST, 2000);
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
        testFileList.appendFile(fs, type, fileInfos, null);
        //logBuilder.append(new Gson().toJson(fileInfos));
        System.out.println("----shenshilong------" + (System.currentTimeMillis() - startTime) + " ms.");
        Path logPath = Paths.get("F:\\temp\\file.log");
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, StandardCharsets.UTF_8)) {
            writer.write(logBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void childPath(String path, LoopFloderEnum type, int sleepTime) {

        Objects.requireNonNull(type, "不能为空！");
        long startTime = System.currentTimeMillis();
        File parentFile = new File(path);
        File[] fs = parentFile.listFiles();
        TestFileList testFileList = new TestFileList();
        testFileList.rootPathLength = path.length();
        List<FileInfo> fileInfos = new ArrayList<>();
        this.startTime = System.currentTimeMillis();
        FileInfo fileInfo = fileType(parentFile, fileInfos, "0");
        testFileList.appendFile(fs, type, fileInfos, sleepTime, fileInfo.getId());
        //logBuilder.append(new Gson().toJson(fileInfos));
        System.out.println("----shenshilong------" + (System.currentTimeMillis() - startTime) + " ms.");
        Path logPath = Paths.get("F:\\temp\\file.log");
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, StandardCharsets.UTF_8)) {
            writer.write(logBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<FileInfo> appendFile(File[] fs, LoopFloderEnum type, List<FileInfo> fileInfos, String parentId) {

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
                appendFile(files, type, fileInfos, parentId);
            }
            fileType(file, fileInfos, parentId);
        }
        return fileInfos;
    }

    private List<FileInfo> appendFile(File[] fs, LoopFloderEnum type, List<FileInfo> fileInfos, int sleepTime,
        String parentId) {

        if (null == fs ||
            fs.length == 0) {
            return Collections.emptyList();
        }
        OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        this.endTime = System.currentTimeMillis();
        if (this.endTime - this.startTime >= 1000) {
            try {
                saveFileInfo(fileInfos);
                TimeUnit.MILLISECONDS.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.startTime = System.currentTimeMillis();
        }

        for (File file : fs) {
            String replaceName = file.getName().replace(" ", "");
            if (FILTER_NAMES.contains(replaceName)) {
                continue;
            }
            String absolutePath = file.getAbsolutePath();
            logBuilder.append(absolutePath.substring(38)).append(property);
            FileInfo fileInfo = fileType(file, fileInfos, parentId);
            if (LoopFloderEnum.ALL_LIST.equals(type)) {
                File[] files = file.listFiles();
                appendFile(files, type, fileInfos, sleepTime, fileInfo.getId());
            }

        }
        return fileInfos;
    }

    private void saveFileInfo(List<FileInfo> fileInfos) {
        Db db = this.storeSystemFile.getDb();
        fileInfos.forEach(fileInfo -> {
            try {
                db.insert(
                    Entity.create("T_FILE_INFO")
                        .set("ID", fileInfo.getId())
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        fileInfos.clear();
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

    public FileInfo fileType(File file, List<FileInfo> fileInfos, String parentId) {

        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(IdUtil.simpleUUID());
        fileInfo.setParentId(parentId);
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
        return fileInfo;
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
