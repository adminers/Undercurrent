package com.qiaweidata.un.plugin;

import cn.hutool.http.HttpUtil;

/**
 * @Title: RestfulFastRequestTest
 * @Description: RestfulFastRequestTest
 * @date: 2023-04-20
 * @version: V1.0
 */
public class RestfulFastRequestTest {

    public static void main(String[] args) {

        String s = HttpUtil.get("https://github.com/adminers/Undercurrent.git");
        System.out.println(s);
    }
}
