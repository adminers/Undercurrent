package com.qiaweidata.un;

import lombok.Getter;

/**
 * 聚合函数枚举
 *
 * @Title: AggregateFunctionEnum
 * @Description: AggregateFunctionEnum
 * @date: 2025-4-07
 * @version:V1.0
 */
@Getter
public enum AggregateFunctionEnum {

    SUM("1"),

    MAX("2"),

    MIN("3"),

    COUNT("4"),

    AVG("5");

    private final String value;

    AggregateFunctionEnum(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        String code = "/**\n * 卡牌背景\n *\n * @author shen\n */\npublic class Card { }";
        String cleanedCode = code
                // 启用DOTALL模式（(?s)），让 . 匹配包括换行符的所有字符
                .replaceAll("(?s)/\\*\\*.*?\\*/", "");
        System.out.println(cleanedCode.trim());
    }
}
