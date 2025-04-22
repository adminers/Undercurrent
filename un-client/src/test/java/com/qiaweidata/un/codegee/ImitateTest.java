package com.qiaweidata.un.codegee;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * @Title: ImitateTest
 * @Description: ImitateTest
 * @date: 2023-04-13
 * @version: V1.0
 */
public class ImitateTest {

    public static void main(String[] args) {

        HashMap<String, Object> paramMap = new HashMap<>();
        String json = new Gson().toJson(paramMap);
        String result2 = HttpRequest.post(null)
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Content-Length", "745")
                .header("Accept-Language", "zh-CN,zh;q=0.9")
                .header("Connection", "keep-alive")
                .header("Host", "wudao.aminer.cn")
                .header("Origin", "https://codegeex.cn")
                .header("Referer", "https://codegeex.cn/")
                .header("sec-ch-ua", "?\"Chromium\";v=\"112\", \"Google Chrome\";v=\"112\", \"Not:A-Brand\";v=\"99\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "cross-site")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36")
                .body(json)
                .execute().body();

        Console.log(result2);
    }
}
