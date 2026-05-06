package com.dpv4.ag;

/**
 * 归并排序器
 * 使用 MergeUtil 进行合并操作
 */
public class MergeSorter {
    /**
     * 对整型数组进行归并排序
     * @param arr 待排序数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        // 创建临时数组用于合并
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            // 递归排序左右两半
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            // 合并两个有序子数组
            MergeUtil.merge(arr, left, mid, right, temp);
        }
    }
}