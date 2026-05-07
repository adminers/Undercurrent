package com.dpv4.ag;

public class RadixSorter {
    /**
     * 对非负整数数组进行 LSD（最低位优先）基数排序
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int max = getMax(arr);
        // 按每个位进行计数排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            CountingSortUtil.countingSortByDigit(arr, exp);
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }
}