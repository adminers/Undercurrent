package com.dpv4.ag.sorter;

import com.dpv4.ag.util.ArrayUtil;
import com.dpv4.ag.util.QuickPartitionUtil;

/**
 * 快速排序器
 * 使用分治策略实现
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public class QuickSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        if (ArrayUtil.isSortedOrEmpty(arr)) {
            return;
        }

        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = QuickPartitionUtil.partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
}