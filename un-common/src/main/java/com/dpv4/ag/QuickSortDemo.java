package com.dpv4.ag;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序演示程序
 * 依赖 QuickSorter 和 QuickPartitionUtil
 */
public class QuickSortDemo {
    public static void main(String[] args) {
        // 生成随机数组
        int[] array = new int[10];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        System.out.println("快速排序前: " + Arrays.toString(array));
        QuickSorter.sort(array);
        System.out.println("快速排序后: " + Arrays.toString(array));
    }
}