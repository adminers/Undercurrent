package com.dpv4.ag;

/**
 * 冒泡排序交换工具类
 */
public class BubbleSwapUtil {
    /**
     * 交换数组中两个位置的元素
     * @param arr 数组
     * @param i 索引1
     * @param j 索引2
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}