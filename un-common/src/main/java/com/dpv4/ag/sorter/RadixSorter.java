package com.dpv4.ag.sorter;

import com.dpv4.ag.util.ArrayUtil;
import com.dpv4.ag.util.CountingSortUtil;

/**
 * 基数排序器
 * 使用 LSD（最低位优先）策略
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public class RadixSorter implements Sorter {

    @Override
    public void sort(int[] arr) {
        if (ArrayUtil.isSortedOrEmpty(arr)) {
            return;
        }

        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            CountingSortUtil.countingSortByDigit(arr, exp);
        }
    }

    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}