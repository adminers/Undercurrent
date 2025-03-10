package com.fly.mail;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.fly.core.utils.HtmlUtil;

/**
 * 
 * 单元测试
 * 
 * @author 00fly
 * @version [版本号, 2020年6月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HtmlUtilTest
{
    private static String html;
    
    @BeforeClass
    public static void init()
    {
        html = "<p>项目仪表盘功能，展示项目的基本信息。</p><p>项目进度，项目基本信息</p><p>#项目仪表盘项目进度  8，8，4</p><p>#项目工时分布，缺陷分布，缺陷分析</p><p>#项目仪表盘统计分析  16，24，16</p>";
    }
    
    @Test
    public void test()
    {
        String text = HtmlUtil.delHtmlTags(html);
        System.out.println(text);
        text = StringEscapeUtils.unescapeHtml4(text);
        String[] textArr = StringUtils.split(text, "#");
        List<Map<String, Object>> taskList = new ArrayList<Map<String, Object>>();
        for (String line : textArr)
        {
            line = StringUtils.trimToEmpty(line);
            System.out.println(line);
            if (StringUtils.isNotBlank(line))
            {
                Map<String, Object> task = new LinkedHashMap<String, Object>(4);
                String[] value = StringUtils.split(line, " ");
                if (value.length == 2 && value[1].split(",").length == 3)
                {
                    String[] moCoins = value[1].split(",");
                    int front = NumberUtils.toInt(moCoins[0]);
                    int back = NumberUtils.toInt(moCoins[1]);
                    int test = NumberUtils.toInt(moCoins[2]);
                    task.put("desc", value[0]);
                    task.put("front", front);
                    task.put("back", back);
                    task.put("test", test);
                    taskList.add(task);
                }
            }
        }
        taskList.forEach(System.out::println);
    }
    
    @Ignore
    @Test
    public void test2()
    {
        System.out.println(",,20".split(",").length);
        System.out.println(StringUtils.split(",,20", ",").length);
    }
}
