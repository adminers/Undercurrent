package com.dpv4.ag;

/**
 * 快速排序分区工具类
 */
public class QuickPartitionUtil {
    /**
     * 对数组的指定区间进行分区操作
     * @param arr 数组
     * @param low 区间下界
     * @param high 区间上界
     * @return 基准元素的最终位置
     */
    public static int partition(int[] arr, int low, int high) {
        // 选择最右侧元素作为基准
        int pivot = arr[high];
        int i = low - 1; // 指向小于基准的区域的末尾

        for (int j = low; j < high; j++) {
            // 当前元素小于或等于基准时，将其交换到左侧区域
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // 将基准放到正确位置
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}