package com.dpv4.ag.demo;

import com.dpv4.ag.sorter.MergeSorter;
import com.dpv4.ag.sorter.Sorter;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序演示程序
 * 
 * @author dpv4
 * @date 2026-05-18
 */
public class MergeSortDemo {

    private static final int ARRAY_SIZE = 10;
    private static final int RANDOM_BOUND = 100;

    public static void main(String[] args) {
        int[] array = generateRandomArray(ARRAY_SIZE);
        System.out.println("归并排序前: " + Arrays.toString(array));

        Sorter sorter = new MergeSorter();
        sorter.sort(array);

        System.out.println("归并排序后: " + Arrays.toString(array));
    }

    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(RANDOM_BOUND);
        }
        return arr;
    }
}