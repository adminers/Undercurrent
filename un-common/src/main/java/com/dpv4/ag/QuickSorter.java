package com.dpv4.ag;

/**
 * 快速排序器
 * 使用 QuickPartitionUtil 进行分区操作
 */
public class QuickSorter {
    /**
     * 对整型数组进行快速排序
     * @param arr 待排序数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 获取分区索引
            int pivotIndex = QuickPartitionUtil.partition(arr, low, high);
            // 递归排序左右子数组
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
}