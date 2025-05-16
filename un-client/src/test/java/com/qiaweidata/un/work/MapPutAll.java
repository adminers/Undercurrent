package com.qiaweidata.un.work;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: MapPutAll
 * @Description: MapPutAll
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2025-05-16
 * @version: V1.0
 */
public class MapPutAll {

    public static void main(String[] args) {

        Map<String, String> name = new HashMap<>();
        name.put("a", "a");
        name.put("b", "b");

        Map<String, String> age = new HashMap<>();
        age.putAll(name);

        name.put("c", "c");
        System.out.println(age);
    }
}
