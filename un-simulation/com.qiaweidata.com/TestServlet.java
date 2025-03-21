package com.fly.hello.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 要使@WebServlet注解生效，启动类必须使用@ServletComponentScan
 * 
 * @author  00fly
 * @version  [版本号, 2023年4月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@WebServlet(value = "/test1")
public class TestServlet extends HttpServlet
{
    private static final long serialVersionUID = 6053239034277485841L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException
    {
        doGet(request, response);
    }
}