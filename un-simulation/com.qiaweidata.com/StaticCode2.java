package com.fly.hello.runner;

import javax.annotation.PostConstruct;

import com.fly.core.utils.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StaticCode2
{
    static
    {
        // IndexController.info() L65
        log.info("==================类初次被加载时执行，仅执行一次");
        log.info("==================StaticCode 22222 static: {}", SpringContextUtils.getActiveProfile());
    }
    
    @PostConstruct
    private void init()
    {
        // 非spring管理，不会执行
        log.info("==================非spring管理，永远不会执行，除非加上@Component注解");
        log.info("==================StaticCode 22222 init: {}", SpringContextUtils.getActiveProfile());
    }
    
    public static void print()
    {
        log.info("==================StaticCode 22222 print: {}", SpringContextUtils.getActiveProfile());
    }
}
