package com.dpv4.ag;

public class CountingSortUtil {
    /**
     * 根据指定位（exp）对数组进行稳定计数排序
     * @param arr 待排序数组
     * @param exp 位权值（1,10,100...）
     */
    public static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // 0-9

        // 统计当前位上各个数字的出现次数
        for (int num : arr) {
            int digit = (num / exp) % 10;
            count[digit]++;
        }
        // 转换为前缀和（便于稳定排序）
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        // 从后向前填充 output（保证稳定性）
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        // 复制回原数组
        System.arraycopy(output, 0, arr, 0, n);
    }
}