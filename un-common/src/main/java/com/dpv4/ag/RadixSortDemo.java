package com.dpv4.ag;

import java.util.Arrays;
import java.util.Random;

public class RadixSortDemo {
    public static void main(String[] args) {
        int[] array = generateRandomArray(10);
        System.out.println("基数排序前: " + Arrays.toString(array));
        RadixSorter.sort(array);
        System.out.println("基数排序后: " + Arrays.toString(array));
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }
}