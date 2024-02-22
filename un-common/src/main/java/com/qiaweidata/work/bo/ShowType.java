package com.qiaweidata.work.bo;

import com.qiaweidata.enums.ShowTypeEnum;

import java.io.Serial;
import java.io.Serializable;

/**
 * @Title: ShowType
 * @Description: ShowType
 * @Company: www.wrenchdata.com
 * @author: shenshilong
 * @date: 2024-02-20
 * @version: V1.0
 */
public class ShowType implements Serializable {

    @Serial
    private static final long serialVersionUID = -7025384013063172230L;

    public ShowType(String showName, ShowTypeEnum type, String value) {
        this.showName = showName;
        this.type = type;
        this.value = value;
    }

    private String showName;

    private ShowTypeEnum type;

    private String value;

    public String getShowName() {
        return showName;
    }

    public ShowType setShowName(String showName) {
        this.showName = showName;
        return this;
    }

    public ShowTypeEnum getType() {
        return type;
    }

    public ShowType setType(ShowTypeEnum type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public ShowType setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "ShowType{" +
                "showName='" + showName + '\'' +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
