package com.dpv4.ag.util;

/**
 * 归并排序合并工具类
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public final class MergeUtil {

    private MergeUtil() {
        // 工具类禁止实例化
    }

    /**
     * 合并两个有序子数组 [left, mid] 和 [mid+1, right]
     * 
     * @param arr   原数组
     * @param left  左子数组起始索引
     * @param mid   中间索引
     * @param right 右子数组结束索引
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        while (j <= right) {
            temp[t++] = arr[j++];
        }

        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }
}