package com.fly.utils;

import java.util.Observable;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 
 * 日志组件
 *
 * @author 00fly
 * @version [版本号, 2018年3月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LogMonitor extends Observable
{
    private String loggerInfo;
    
    public String getLoggerInfo()
    {
        return loggerInfo;
    }
    
    public void setLoggerInfo(String loggerInfo)
    {
        this.loggerInfo = loggerInfo;
        setChanged();
        notifyObservers(DateFormatUtils.format(System.currentTimeMillis(), "HH:mm:ss ") + loggerInfo);
    }
}
