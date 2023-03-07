package com.qiaweidata.un.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author lqq
 * @date 2021/7/5 11:00
 */
public class DateUtil {
  
  // 时间格式为：yyyy-MM-dd HH:mm:ss
  private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  
  // 获取字符串的现在时间
  public static String formatNow() {
        return LocalDateTime.now().format(DEFAULT_FORMATTER);
  }
  
  // 根据时间、格式获取字符串的时间
  public static String formatTime(LocalDateTime time, String format) {
        return time.format(DateTimeFormatter.ofPattern(format));
  }
  
  // 时间的字符串和Long时间戳互转
  public static String formatLongTime(long time) {
        return DEFAULT_FORMATTER.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
  }
 
}
