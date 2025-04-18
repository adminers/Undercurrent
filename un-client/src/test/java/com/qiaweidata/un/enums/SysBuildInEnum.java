
package com.qiaweidata.un.enums;

import lombok.Getter;


@Getter
public enum SysBuildInEnum {


    BUILDT("a", "1"),

    BUILDE("b", "2"),


    BUILDDE("c", "3");

    private final String value;

    private final String name;

    SysBuildInEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }
}
