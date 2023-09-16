package com.qiaweidata.un.pojo.util;

import static com.qiaweidata.un.utils.DiskUtil.gigaByte;

/**
 * @Title: MemoryInfo
 * @Description: MemoryInfo
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2023-03-16
 * @version: V1.0
 */
public class MemoryInfo {

    /**
     * 内存总量
     */
    private long totalMemorySize;

    /**
     * 内存可用
     */
    private long freeMemorySize;

    public MemoryInfo() {
    }

    public MemoryInfo(long totalMemorySize, long freeMemorySize) {
        this.totalMemorySize = totalMemorySize;
        this.freeMemorySize = freeMemorySize;
    }

    /**
     * 获取 内存总量
     *
     * @return totalMemorySize 内存总量
     */
    public long getTotalMemorySize() {
        return this.totalMemorySize;
    }

    /**
     * 设置 内存总量
     *
     * @param totalMemorySize 内存总量
     */
    public void setTotalMemorySize(long totalMemorySize) {
        this.totalMemorySize = totalMemorySize;
    }

    /**
     * 获取 内存可用
     *
     * @return freeMemorySize 内存可用
     */
    public long getFreeMemorySize() {
        return this.freeMemorySize;
    }

    /**
     * 设置 内存可用
     *
     * @param freeMemorySize 内存可用
     */
    public void setFreeMemorySize(long freeMemorySize) {
        this.freeMemorySize = freeMemorySize;
    }

    @Override
    public String toString() {
        return "MemoryInfo{" +
            "totalMemorySize=" + gigaByte(totalMemorySize) +
            ", freeMemorySize=" + gigaByte(freeMemorySize) +
            '}';
    }
}
