package com.qiaweidata.undercurrent.ai;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import com.qiaweidata.undercurrent.pojo.ai.JsonRootBean;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: ImitateTest
 * @Description: ImitateTest
 * @Company: www.wrenchdata.com
 * @author: shenshilong
 * @date: 2023-04-13
 * @version: V1.0
 */
public class ImitateCode {

    public static String IS_RUN = "1";

    public static AtomicInteger LINE_INDEX = new AtomicInteger(0);

    private final StringBuilder code = new StringBuilder();

    public static final Map<String, String> properties = new HashMap<>(2);
    public static final Map<String, String> HEADER_PROPERTIES = new HashMap<>(2);

    public String text;

    private Gson GSON = new Gson();

    static {

        //读取resources/config目录下的application.properties
        ResourceBundle rb2 = ResourceBundle.getBundle("ai");
        for(String key : rb2.keySet()){
            String value = rb2.getString(key);
            properties.put(key, value);
        }

        ResourceBundle rbHeader = ResourceBundle.getBundle("header");
        for(String key : rbHeader.keySet()){
            String value = rbHeader.getString(key);
            HEADER_PROPERTIES.put(key, value);
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        new ImitateCode().timer();
    }

    public void timer() {

        if ( null == this.text ||
            "".equals(this.text)) {
            this.text = "// " + FileUtil.readUtf8Lines(properties.get("fileTrain")).get(LINE_INDEX.get());
            this.code.setLength(0);
        }

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("n", 1);
        paramMap.put("lang", properties.get("lang"));
        paramMap.put("prompt", this.text + this.code);
        String json = new Gson().toJson(paramMap);
        StringBuffer allParam = new StringBuffer();
        paramMap.forEach((k, v) -> {allParam.append(k).append("=").append(v).append("&");});
        allParam.deleteCharAt(allParam.length() - 1);
        String result2 = null;
        try {
                HttpRequest hp = HttpRequest.post(properties.get("url") + "?" + URLEncoder.encode( allParam.toString(), "UTF-8" ) );
                HEADER_PROPERTIES.forEach((k, v) -> {
                    hp.header(k, v);
                });
                result2 = hp.body(json)
                .execute()
                .body();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> map = GSON.fromJson(result2, Map.class);
        if ("success".equals(map.get("message"))) {
            JsonRootBean jsonRootBean = GSON.fromJson(result2, JsonRootBean.class);
            appendCode(jsonRootBean);
        }
    }

    private void appendCode(JsonRootBean jsonRootBean) {

        if (null == jsonRootBean) {
            return;
        }

        String code = jsonRootBean.getResult().getOutput().getCode().get(0);
        if ("".equals(code)) {
            LINE_INDEX.getAndIncrement();
        }
        this.code.append(code);
    }

    /**
     * 获取
     *
     * @return text
     */
    public String getText() {
        return this.text;
    }

    /**
     * 设置
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }
}
