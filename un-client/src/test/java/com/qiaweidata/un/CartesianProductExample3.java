package com.qiaweidata.un;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CartesianProductExample3 {
    public static void main(String[] args) {
        // 初始化数据
        Map<String, List<String>> datas = new LinkedHashMap<>();
        datas.put("asdfasdfasdf", Arrays.asList("中国人", "德国人"));
        datas.put("bffffffff", Arrays.asList("小道", "打稿", "批发", "办法"));
        datas.put("ayhjfgjhfgjh", Arrays.asList("a", "b"));  // 可以添加更多的集合

        // 生成笛卡尔积
        List<List<String>> cartesianProduct = new ArrayList<>();
        generateCartesianProduct(new ArrayList<>(datas.values()), 0, new ArrayList<>(), cartesianProduct);

        Map<String, List<String>> newDatas = new LinkedHashMap<>();
        for (String key : datas.keySet()) {
            newDatas.put(key, new ArrayList<>());
        }

        // 输出结果
        for (List<String> combination : cartesianProduct) {
            StringBuilder result = new StringBuilder();
            int index = 0;
            for (String key : datas.keySet()) {
                // 将每个组合的元素与对应的键连接
                result.append(key).append(": ").append(combination.get(index)).append(" | ");
                newDatas.get(key).add(combination.get(index));
                index++;
            }
            // 去掉最后的 " | "
            System.out.println(result.substring(0, result.length() - 3));
        }

        System.out.println(1);
    }
    private static void generateCartesianProduct(List<List<String>> lists, int index, List<String> current, List<List<String>> result) {

        // 如果当前索引等于列表的大小，表示生成了一个完整的组合
        if (index == lists.size()) {
            result.add(new ArrayList<>(current));
            return;
        }
        // 遍历当前列表的每个元素
        for (String element : lists.get(index)) {
            current.add(element);
            generateCartesianProduct(lists, index + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}