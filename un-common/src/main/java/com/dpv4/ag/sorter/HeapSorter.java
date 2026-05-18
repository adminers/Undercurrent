package com.dpv4.ag.sorter;

import com.dpv4.ag.util.ArrayUtil;
import com.dpv4.ag.util.HeapifyUtil;

/**
 * 堆排序器
 * 使用最大堆实现
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public class HeapSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        if (ArrayUtil.isSortedOrEmpty(arr)) {
            return;
        }

        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            HeapifyUtil.heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            ArrayUtil.swap(arr, 0, i);
            HeapifyUtil.heapify(arr, i, 0);
        }
    }
}