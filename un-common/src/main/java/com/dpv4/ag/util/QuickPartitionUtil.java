package com.dpv4.ag.util;

/**
 * 快速排序分区工具类
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public final class QuickPartitionUtil {

    private QuickPartitionUtil() {
        // 工具类禁止实例化
    }

    /**
     * 对数组的指定区间进行分区操作
     * 
     * @param arr  数组
     * @param low  区间下界
     * @param high 区间上界
     * @return 基准元素的最终位置
     */
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                ArrayUtil.swap(arr, i, j);
            }
        }

        ArrayUtil.swap(arr, i + 1, high);
        return i + 1;
    }
}