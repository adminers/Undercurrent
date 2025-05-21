package com.qiaweidata.un;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: CommnSplit
 * @Description: CommnSplit
 * @date: 2025-04-23
 * @version: V1.0
 */
public class CommnSplit {

    public static void main(String[] args) {

        List<String> arr = Arrays.asList("a");
        System.out.println(String.join( " and ", arr));

    }
}
