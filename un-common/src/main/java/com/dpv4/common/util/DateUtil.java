package com.dpv4.common.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";

    private DateUtil() {
    }

    public static String format(LocalDateTime date) {
        return format(date, DEFAULT_PATTERN);
    }

    public static String format(LocalDateTime date, String pattern) {
        if (date == null) {
            return null;
        }
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatDate(LocalDateTime date) {
        return format(date, DATE_PATTERN);
    }

    public static String formatTime(LocalDateTime date) {
        return format(date, TIME_PATTERN);
    }

    public static LocalDateTime parse(String dateStr) {
        return parse(dateStr, DEFAULT_PATTERN);
    }

    public static LocalDateTime parse(String dateStr, String pattern) {
        if (StringUtil.isBlank(dateStr)) {
            return null;
        }
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static long between(LocalDateTime start, LocalDateTime end, ChronoUnit unit) {
        if (start == null || end == null) {
            return 0;
        }
        return unit.between(start, end);
    }

    public static long daysBetween(LocalDateTime start, LocalDateTime end) {
        return between(start, end, ChronoUnit.DAYS);
    }

    public static long hoursBetween(LocalDateTime start, LocalDateTime end) {
        return between(start, end, ChronoUnit.HOURS);
    }

    public static long minutesBetween(LocalDateTime start, LocalDateTime end) {
        return between(start, end, ChronoUnit.MINUTES);
    }

    public static String getRelativeTime(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        long minutes = minutesBetween(date, now);
        
        if (minutes < 1) {
            return "刚刚";
        }
        if (minutes < 60) {
            return minutes + "分钟前";
        }
        long hours = minutes / 60;
        if (hours < 24) {
            return hours + "小时前";
        }
        long days = hours / 24;
        if (days < 30) {
            return days + "天前";
        }
        return formatDate(date);
    }
}
