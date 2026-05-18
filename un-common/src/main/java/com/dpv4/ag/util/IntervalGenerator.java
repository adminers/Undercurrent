package com.dpv4.ag.util;

/**
 * 间隔生成器
 * 用于生成希尔排序的增量序列
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public final class IntervalGenerator {

    private IntervalGenerator() {
        // 工具类禁止实例化
    }

    /**
     * 获取初始间隔值（希尔增量序列）
     * 
     * @param length 数组长度
     * @return 初始间隔
     */
    public static int getInitialGap(int length) {
        return length / 2;
    }

    /**
     * 生成下一次的间隔值（希尔增量序列）
     * 
     * @param currentGap 当前间隔
     * @return 下一个间隔
     */
    public static int nextGap(int currentGap) {
        return currentGap / 2;
    }
}