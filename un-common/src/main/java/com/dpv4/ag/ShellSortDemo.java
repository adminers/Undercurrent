package com.dpv4.ag;

import java.util.Arrays;
import java.util.Random;

public class ShellSortDemo {
    public static void main(String[] args) {
        int[] array = generateRandomArray(10);
        System.out.println("希尔排序前: " + Arrays.toString(array));
        ShellSorter.sort(array);
        System.out.println("希尔排序后: " + Arrays.toString(array));
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }
}