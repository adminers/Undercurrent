package com.dpv4.ag.util;

/**
 * 数组工具类
 * 提供数组操作的通用方法
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public final class ArrayUtil {

    private ArrayUtil() {
        // 工具类禁止实例化
    }

    /**
     * 交换数组中两个位置的元素
     * 
     * @param arr 数组
     * @param i 索引1
     * @param j 索引2
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 检查数组是否为空或长度为0
     * 
     * @param arr 数组
     * @return 是否为空
     */
    public static boolean isEmpty(int[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * 检查数组是否为空或长度小于等于1（已排序状态）
     * 
     * @param arr 数组
     * @return 是否为已排序状态或空
     */
    public static boolean isSortedOrEmpty(int[] arr) {
        return arr == null || arr.length <= 1;
    }
}