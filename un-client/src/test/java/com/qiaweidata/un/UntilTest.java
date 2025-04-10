package com.qiaweidata.un;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: UntilTest
 * @Description: UntilTest
 * @Company: www.wrenchdata.com
 * @author: shenshilong
 * @date: 2025-04-08
 * @version: V1.0
 */
public class UntilTest {

    private static String value_ = "A3=kLz8_value";

    private static String valueType = "value";


    public static void main(String[] args) {


        Map<String, Object> root = new HashMap<>();

        Map<String, Object> nextMap = new HashMap<>();
        List<String> columns = Arrays.asList("name", "changdao", "age", "value");

        String data = "{\n" +
                "\t\"name\": [\"中国人\", \"中国人\", \"俄罗斯人\", \"俄罗斯人\", \"德国人\", \"西班牙人\"],\n" +
                "\t\"changdao\": [\"小道\", \"打稿\", \"批发\", \"批发\", \"打稿\", \"办法\"],\n" +
                "\t\"age\": [\"三\", \"四\", \"五\", \"六\", \"七\", \"八\"]\n" +
                "}";
        Map<String, List<String>> datas = null;
        for (int i = 0; i < 5; i++) {
            String baseDataKey = "";
            String baseDataValue = "";
            for (int j = 0; j < columns.size(); j++) {
                String column = columns.get(j);
                String key = valueType.equals(column) ? value_ : column;
                if (!value_.equals(key)) {
                    List<String> lineDatas = datas.get(key);
                    String columnValue = lineDatas.get(i);
                    if (root.isEmpty()) {


                        Map<String, Object> childMap = new HashMap<>();
                        root.put(column, childMap);

                        nextMap = new HashMap<>();
                        childMap.put(columnValue, nextMap);

                    } else if (root.containsKey(column)) {
                        Map<String, Object> childMap = (Map<String, Object>) root.get(column);
                        nextMap = new HashMap<>();
                        childMap.put(columnValue, nextMap);
                    } else {

                        Map<String, Object> childMap = new HashMap<>();
                        nextMap.put(column, childMap);

                        nextMap = new HashMap<>();
                        childMap.put(columnValue, nextMap);
                    }
                }
            }

        }

        System.out.println(1);
    }
}
