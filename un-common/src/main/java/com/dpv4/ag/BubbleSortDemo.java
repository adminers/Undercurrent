package com.dpv4.ag;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序演示程序
 * 依赖 BubbleSorter 和 BubbleSwapUtil
 */
public class BubbleSortDemo {
    public static void main(String[] args) {
        // 生成随机数组
        int[] array = new int[10];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        System.out.println("冒泡排序前: " + Arrays.toString(array));
        BubbleSorter.sort(array);
        System.out.println("冒泡排序后: " + Arrays.toString(array));
    }
}