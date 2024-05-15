package com.fly.core;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * AuthorizationInterceptor
 * 
 * @author 00fly
 * @version [版本号, 2019年7月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter
{
    @Autowired
    ApplicationContext applicationContext;
    
    @Value("${server.port}")
    String port;
    
    @Autowired
    HttpSession session;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception
    {
        if (session.getAttribute("urls") == null)
        {
            session.setAttribute("urls", AnnotationHelper.getRequestMappingURL(applicationContext));
            session.setAttribute("port", port);
            
            File war = new File("bootj2cache.war");
            if (war.exists())
            {
                session.setAttribute("path", war.getAbsoluteFile());
                session.setAttribute("time", DateFormatUtils.format(war.lastModified(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
        return true;
    }
}
