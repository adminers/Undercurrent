package com.qiaweidata.un;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartesianProductExample {
    public static void main(String[] args) {
        // 定义集合并放入 Map
        Map<String, List<String>> res = new HashMap<>();
        res.put("code1", Arrays.asList("中国人", "德国人", "西班牙人", "俄罗斯人"));
        res.put("code2", Arrays.asList("百合花", "樱桃花"));
//        res.put("code3", Arrays.asList("a", "b", "c"));
        // 定义坐标 Map
        Map<String, Integer> move = new HashMap<>();
        move.put("code1", 0);
        move.put("code2", 0);
//        move.put("code3", 0);
        // 记录结果
        List<String> results = new ArrayList<>();
        // 循环直到所有组合都被取完
        while (true) {
            // 获取当前坐标的值
            String name = res.get("code1").get(move.get("code1"));
            String flower = res.get("code2").get(move.get("code2"));
//            String letter = res.get("code3").get(move.get("code3"));
            // 记录结果
            results.add(name + "-" + flower + "-" + "letter");
            // 更新坐标
            if (!updateMove(move, res)) {
                break; // 如果没有更多组合，退出循环
            }
        }
        // 输出结果
        for (String result : results) {
            System.out.println(result);
        }
    }
    private static boolean updateMove(Map<String, Integer> move, Map<String, List<String>> res) {
        // 从后向前更新坐标
        for (String key : List.of(/*"code3",*/ "code2", "code1")) {
            int currentIndex = move.get(key);
            if (currentIndex < res.get(key).size() - 1) {
                move.put(key, currentIndex + 1); // 移动到下一个
                return true;
            } else {
                // 如果当前索引已到达最后一个元素，重置为 0
                move.put(key, 0);
            }
        }
        return false; // 如果所有索引都重置为 0，表示所有组合都已取完
    }
}