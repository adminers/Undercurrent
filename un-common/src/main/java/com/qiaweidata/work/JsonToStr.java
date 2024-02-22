package com.qiaweidata.work;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiaweidata.enums.ShowTypeEnum;
import com.qiaweidata.work.bo.ShowType;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonToStr {

    public static void main(String[] args) {

        List<ShowType> showTypes = new ArrayList<>();
        showTypes.add(new ShowType("提交人", ShowTypeEnum.DEFAULT, "fillUser"));
        showTypes.add(new ShowType("-", ShowTypeEnum.TXT, "-"));
        showTypes.add(new ShowType("表单名称", ShowTypeEnum.DEFAULT, "formName"));
        showTypes.add(new ShowType("单行输入框", ShowTypeEnum.ITEM, "I000001"));
        showTypes.add(new ShowType("数字输入框", ShowTypeEnum.ITEM, "I000002"));
        Gson gson = new Gson();
        String json = gson.toJson(showTypes);
        System.out.println(json);
        // 创建Type对象，表示List套对象的类型

        Type listType = new TypeToken<List<ShowType>>(){}.getType();

        // 将JSON字符串转换为List套对象
        List<ShowType> personList = gson.fromJson(json, listType);

        // 打印List套对象的内容
        for (ShowType person : personList) {
            System.out.println("Name: " + person.getShowName() + ", Age: " + person.getType());
        }

        String a = "[{\"showName\":\"单行输入框\",\"type\":\"item\",\"value\":\"I000001\"},{\"showName\":\"数字输入框\",\"type\":\"item\",\"value\":\"I000002\"},{\"showName\":\"日期\",\"type\":\"item\",\"value\":\"I000002\"}]";
         Type listType1 = new TypeToken<List<ShowType>>(){}.getType();

        // 将JSON字符串转换为List套对象
        List<ShowType> personList1 = gson.fromJson(a, listType1);
        personList = personList;

    }
}
