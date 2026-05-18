package com.dpv4.ag.util;

/**
 * 计数排序工具类
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public final class CountingSortUtil {

    private CountingSortUtil() {
        // 工具类禁止实例化
    }

    /**
     * 根据指定位（exp）对数组进行稳定计数排序
     * 
     * @param arr 待排序数组
     * @param exp 位权值（1, 10, 100...）
     */
    public static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }
}