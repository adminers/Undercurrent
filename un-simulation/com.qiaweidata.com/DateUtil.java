package com.fly.procode.core;

import java.text.SimpleDateFormat;

public class DateUtil
{
    public final static String MMDDHHMM = "_MMddHHmm";
    
    public static final String getCurrDateTimeStr(String formatStr)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.format(System.currentTimeMillis());
    }
    
    public static final String getCurrDateTimeStr()
    {
        return getCurrDateTimeStr(MMDDHHMM);
    }
}
