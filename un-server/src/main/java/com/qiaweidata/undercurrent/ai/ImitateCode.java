package com.qiaweidata.undercurrent.ai;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import com.google.gson.Gson;
import com.qiaweidata.undercurrent.pojo.ai.JsonRootBean;
import com.qiaweidata.undercurrent.youdao.FanyiV3Demo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.html.HTML;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Title: ImitateTest
 * @Description: ImitateTest
 * @Company: www.qiaweidata.com
 * @author: shenshilong
 * @date: 2023-04-13
 * @version: V1.0
 */
public class ImitateCode {

    private static final Logger log = LogManager.getLogger(ImitateCode.class);

    /**
     * 是否运行
     */
    public static String IS_RUN = "1";

    public static AtomicInteger LINE_INDEX = new AtomicInteger(0);

    private final StringBuilder code = new StringBuilder();

    public static final Map<String, String> properties = new HashMap<>(2);
    public static final Map<String, String> HEADER_PROPERTIES = new HashMap<>(2);

    public String text;

    private static final Gson GSON = new Gson();

    private static final List<Character> LETTER = new ArrayList<>(26 + 26);

    /**
     * 变动的,都放这个里面
     * 0 = RUN 标志
     * 1 = 保存地址
     * 2 = 训练文本的当前行号
     */
    public static final List<String> PRO_FILE_CONFIG = new ArrayList<>(10);

    /**
     * 替换 html 的模版
     */
    public static final String HTML_TEMP = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "  <title>Highlight.js Example</title>\n" + "  <!-- 引入 highlight.js 样式文件 -->\n" + "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.2.0/styles/monokai.min.css\">\n" + "  <style>\n" + "    /* 增加代码行之间的距离 */\n" + "    pre {\n" + "      line-height: 1.5em;\n" + "    }\n" + "  </style>\n" + "  <!-- 引入 highlight.js 脚本文件 -->\n" + "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.2.0/highlight.min.js\"></script>\n" + "  <script>hljs.initHighlightingOnLoad();</script>\n" + "</head>\n" + "<body>\n" + "\n" + "  <h1>HTML 代码示例</h1>\n" + "\n" + "  <pre><code class=\"java\">\n" + " %s   \n" + "  </code></pre>\n" + "\n" + "</body>\n" + "</html>";

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

        for (int i = 0; i < 26; i++) {
            LETTER.add((char) ('A' + i));
            LETTER.add((char) ('a' + i));
        }

        PRO_FILE_CONFIG.addAll(FileUtil.readUtf8Lines(properties.get("fileRun")));
        LINE_INDEX.set(Integer.valueOf(FileUtil.readUtf8String(ImitateCode.properties.get("lineFile"))));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        System.out.println(FanyiV3Demo.getEnglish("中国字"));

        if (true) {
            return;
        }

        // new ImitateCode().timer();
        Stream<Character> characterStream = "asdfasd asdfe2323;".chars().mapToObj(c -> (char) c);
        try {
            System.out.println(new File("E:\\temp").getCanonicalPath());;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String body = HttpRequest.post(properties.get("gitUrl") + "/git/updateCode")
            .header("token", properties.get("gitToken"))
            .execute()
            .body();
        System.out.println(body);

    }

    public void timer() {

        if ( null == this.text ||
            "".equals(this.text)) {
            List<String> txt = FileUtil.readUtf8Lines(properties.get("fileTrain"));
            if (LINE_INDEX.get() >= txt.size()) {
//                log.info("parsed to end of file. file line is {}", LINE_INDEX.get());
                return;
            }
            this.text = "// " + txt.get(LINE_INDEX.get()) + "\\n";
            this.code.setLength(0);
        }

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put(properties.get("HTFC"), Integer.valueOf(properties.get("HTFD")));
        paramMap.put(properties.get("HTFA"), properties.get("HTFB"));
        paramMap.put(properties.get("HTFE"), this.text + this.code);
        String json = new Gson().toJson(paramMap);
        StringBuffer allParam = new StringBuffer();
        paramMap.forEach((k, v) -> {allParam.append(k).append("=").append(v).append("&");});
        allParam.deleteCharAt(allParam.length() - 1);
        String result2 = null;
        HttpRequest hp = HttpRequest.post(properties.get("url") + "?" + URLEncoder.encode(allParam.toString(), StandardCharsets.UTF_8) );
        HEADER_PROPERTIES.forEach((k, v) -> {
            hp.header(k, v);
        });
        hp.header("Content-Length", String.valueOf(allParam.length()));
        result2 = hp.body(json)
        .execute()
        .body();
        String badRequest = "<h1><p>Bad Request</p></h1>";
        if (null == result2 ||
            result2.indexOf(badRequest) >= 0) {
            LINE_INDEX.getAndIncrement();
            this.code.append(badRequest);
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

    public void save() {

        log.info("save code");
        String savePath = PRO_FILE_CONFIG.get(1);
        StringBuilder name = new StringBuilder(60);
        Stream<Character> charStream = this.text.chars().mapToObj(c -> (char) c);
        charStream.forEach(s -> {
            if (name.length() == 60) {
                return;
            }
            if (LETTER.contains(s)) {
                name.append(s);
            }
        });
        name.append(".java");
        FileUtil.writeString(
            String.format(HTML_TEMP, this.text + "\n" + this.code),
            FileUtil.newFile(savePath + File.separatorChar + name),
            StandardCharsets.UTF_8
        );
    }

    /**
     *
     * @return text
     */
    public String getText() {
        return this.text;
    }

    /**
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    public StringBuilder getCode() {
        return this.code;
    }
}
