package com.dpv4.ag.util;

/**
 * 堆化工具类
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public final class HeapifyUtil {

    private HeapifyUtil() {
        // 工具类禁止实例化
    }

    /**
     * 对以 root 为根的子树进行堆化（最大堆）
     * 
     * @param arr 数组
     * @param n   堆的大小
     * @param root 当前子树的根索引
     */
    public static void heapify(int[] arr, int n, int root) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != root) {
            ArrayUtil.swap(arr, root, largest);
            heapify(arr, n, largest);
        }
    }
}