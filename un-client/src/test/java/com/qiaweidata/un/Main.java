package com.qiaweidata.un;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static long l;
    static int k;
    static int c;
    static Long[] array;
    static long index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Long.parseLong(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        array = new Long[k+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            array[i] = Long.parseLong(st.nextToken());
        }
        array[k] = 0L;
        Arrays.sort(array, Collections.reverseOrder());

        long left = 0;
        long right = l + 1;

//        System.out.println(calc(5L));
        while(left < right){
            long mid = (left + right) / 2;
            if(calc(mid)){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        System.out.println(right + " " + index);
    }

    public static boolean calc(long dist){
        long length = 0;
        long before = l;
        int count = 0;
        long first = -1L;
        for(int i = 0; i <= k; i++){
            if(length + (before - array[i]) > dist){
                length = (before - array[i]);
                first = before;
//                System.out.println(before + " " + array[i]);
                count++;
            }
            else{
                length += (before - array[i]);
            }
            if(count > c || length > dist){
                return false;
            }
            before = array[i];
        }
        if(count < c){
            first = array[k-1];
        }
        index = first;
        return true;
    }
}