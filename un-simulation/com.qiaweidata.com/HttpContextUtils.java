package com.fly.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpContextUtils
{
    public static HttpServletRequest getHttpServletRequest()
    {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return (servletRequestAttributes != null ? servletRequestAttributes.getRequest() : null);
    }
}
