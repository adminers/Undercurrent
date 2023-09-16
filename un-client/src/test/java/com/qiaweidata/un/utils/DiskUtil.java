package com.qiaweidata.un.utils;

import com.qiaweidata.un.pojo.util.MemoryInfo;
import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

/**
 * @Title:
 * @Description:
 * @Company:www.qiaweidata.com
 * @author:shenshilong
 * @date:2023-3-16
 * @version:V1.0
 */
public class DiskUtil {
    public static void main(String[] args) throws IOException {
        rootFolderInfo();
        memoryInfo().toString();
    }

    /**
     *
     * @throws IOException
     */
    public static void rootFolderInfo() throws IOException {

        File[] disks = File.listRoots();
        for (File file : disks) {
            // 获取盘符
            System.out.print(file.getCanonicalPath() + "   ");
            // 获取总容量
            long totalSpace = file.getTotalSpace();
            // 获取剩余容量
            long usableSpace = file.getUsableSpace();
            // 获取已经使用的容量
            long freeSpace = totalSpace - usableSpace;
        }
    }

    /**
     * 获取内存使用情况
     *
     * @return 内存对象信息
     */
    public static MemoryInfo memoryInfo() {

        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return new MemoryInfo(mem.getTotalMemorySize(), mem.getFreeMemorySize());
    }

    /**
     * GB 打印
     *
     * @param fileSize 文件字节大小
     * @return
     */
    public static String gigaByte(long fileSize) {

        return fileSize / 1024 / 1024 / 1024 + "GB";
    }
}
