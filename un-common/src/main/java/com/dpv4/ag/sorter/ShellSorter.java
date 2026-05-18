package com.dpv4.ag.sorter;

import com.dpv4.ag.util.ArrayUtil;

/**
 * 希尔排序器
 * 使用希尔增量序列
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public class ShellSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        if (ArrayUtil.isSortedOrEmpty(arr)) {
            return;
        }

        int n = arr.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            gap /= 2;
        }
    }
}