package com.fly.hello.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

@Component
public class DynamicRegServlet implements ServletContextInitializer
{
    @Override
    public void onStartup(ServletContext servletContext)
        throws ServletException
    {
        ServletRegistration h2Servlet = servletContext.addServlet("test", TestServlet.class);
        h2Servlet.addMapping("/test");
    }
}