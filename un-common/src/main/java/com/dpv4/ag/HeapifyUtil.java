package com.dpv4.ag;

public class HeapifyUtil {
    /**
     * 对以 root 为根的子树进行堆化（最大堆）
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
            swap(arr, root, largest);
            heapify(arr, n, largest); // 递归调整受影响子树
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}