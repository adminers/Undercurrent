package com.dpv4.ag;

public class HeapSorter {
    /**
     * 对整型数组进行堆排序
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        int n = arr.length;
        // 建堆（从最后一个非叶子节点开始）
        for (int i = n / 2 - 1; i >= 0; i--) {
            HeapifyUtil.heapify(arr, n, i);
        }
        // 依次将堆顶元素交换到末尾，并重新调整堆
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            HeapifyUtil.heapify(arr, i, 0);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}