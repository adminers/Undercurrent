package com.dpv4.ag;

public class ShellSorter {
    /**
     * 使用希尔增量序列（gap = gap/2）进行排序
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int n = arr.length;
        int gap = n / 2;  // 初始增量
        while (gap > 0) {
            // 对每个子序列进行插入排序
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                // 插入排序（步长为 gap）
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            gap /= 2;  // 缩小增量
        }
    }
}