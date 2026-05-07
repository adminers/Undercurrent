package com.dpv4.ag;

public class IntervalGenerator {
    /**
     * 生成下一次的间隔值（希尔增量序列）
     */
    public static int nextGap(int currentGap) {
        return currentGap / 2;
    }

    public static int getInitialGap(int length) {
        return length / 2;
    }
}