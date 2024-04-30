package com.fly.core.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * WebUtils
 * 
 * @author 00fly
 * @version [版本号, 2022年3月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class WebUtils
{
    /**
     * 获取HttpServletRequest
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static HttpServletRequest getHttpServletRequest()
    {
        return HttpRequestUtils.getHttpServletRequest();
    }
    
    /**
     * 判断一个请求是否是Ajax请求
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isAjaxRequest()
    {
        HttpServletRequest request = getHttpServletRequest();
        return request != null && "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }
    
    /**
     * 判断一个请求是否是Ajax请求
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isAjaxRequest(HttpServletRequest request)
    {
        return request != null && "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }
}
