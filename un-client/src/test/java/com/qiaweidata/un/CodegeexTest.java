package com.qiaweidata.un;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: CodegeexTest
 * @Description: CodegeexTest
 * @Company: www.wrenchdata.com
 * @author: shenshilong
 * @date: 2023-04-08
 * @version: V1.0
 */
public class CodegeexTest {

    public static void main(String[] args) {
        String formula = "1 - 2/1";
        Pattern pattern = Pattern.compile("([-+*/])\\1");
        Matcher matcher = pattern.matcher(formula);
        if (matcher.find()) {
            String operator = matcher.group(1);
            String coefficient = formula.substring(0, matcher.start());
            String denominator = formula.substring(matcher.end()).replaceAll("/", "");
            System.out.printf("%s%s%s =%s%s%s%s.\n",
                coefficient, operator, denominator,
                coefficient.replaceAll("\\*", ""), operator, denominator.replaceAll("\\*", ""),
                "=");
        } else {
            System.out.println("No match!");
        }
    }
}
