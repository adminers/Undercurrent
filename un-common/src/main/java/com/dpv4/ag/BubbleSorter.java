package com.dpv4.ag;

/**
 * 冒泡排序器
 * 使用 BubbleSwapUtil 进行元素交换
 */
public class BubbleSorter {
    /**
     * 对整型数组进行冒泡排序
     * @param arr 待排序数组
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        int n = arr.length;
        // 外层循环控制比较轮数
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            // 内层循环进行相邻元素比较
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    BubbleSwapUtil.swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            // 如果没有发生交换，说明数组已经有序
            if (!swapped) {
                break;
            }
        }
    }
}