/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */
package com.fly.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * IP地址
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
public class IPUtils
{
    /**
     * 获取IP地址
     * 
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request)
    {
        String ip = null;
        try
        {
            ip = request.getHeader("x-forwarded-for");
            // log.info("★★★★ x-forwarded-for: {}", ip);
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("Proxy-Client-IP");
                // log.info("★★★★ Proxy-Client-IP: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("WL-Proxy-Client-IP");
                // log.info("★★★★ WL-Proxy-Client-IP: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_CLIENT_IP");
                // log.info("★★★★ HTTP_CLIENT_IP: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
                // log.info("★★★★ HTTP_X_FORWARDED_FOR: {}", ip);
            }
            if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip))
            {
                ip = request.getRemoteAddr();
                // log.info("★★★★ RemoteAddr: {}", ip);
            }
        }
        catch (Exception e)
        {
            log.error("IPUtils ERROR ", e);
        }
        // 使用代理，则获取第一个IP地址
        if (StringUtils.isNotBlank(ip) && ip.length() > 15)
        {
            if (ip.indexOf(",") > 0)
            {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
