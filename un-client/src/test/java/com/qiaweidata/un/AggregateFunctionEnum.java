package com.qiaweidata.un;

import lombok.Getter;

/**
 * 聚合函数枚举
 *
 * @Title: AggregateFunctionEnum
 * @Description: AggregateFunctionEnum
 * @Company:www.wrenchdata.com
 * @author:shenshilong
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
}
