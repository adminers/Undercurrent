package com.dpv4.ag;

/**
 * 归并排序合并工具类
 */
public class MergeUtil {
    /**
     * 合并两个有序子数组 [left, mid] 和 [mid+1, right]
     * @param arr 原数组
     * @param left 左子数组起始索引
     * @param mid 中间索引
     * @param right 右子数组结束索引
     * @param temp 临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;      // 左子数组起始索引
        int j = mid + 1;   // 右子数组起始索引
        int t = 0;         // 临时数组索引

        // 比较两个子数组的元素，将较小的放入临时数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        // 将左子数组剩余元素复制到临时数组
        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        // 将右子数组剩余元素复制到临时数组
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        // 将临时数组中的元素复制回原数组
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }
}