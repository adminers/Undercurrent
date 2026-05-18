package com.dpv4.ag.sorter;

import com.dpv4.ag.util.ArrayUtil;
import com.dpv4.ag.util.MergeUtil;

/**
 * 归并排序器
 * 使用分治策略实现
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public class MergeSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        if (ArrayUtil.isSortedOrEmpty(arr)) {
            return;
        }

        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            MergeUtil.merge(arr, left, mid, right, temp);
        }
    }
}