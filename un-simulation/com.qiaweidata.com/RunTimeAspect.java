package com.fly.core.aspect;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.fly.core.utils.HttpRequestUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * aop 打印运行时间
 * 
 * @author 00fly
 * @version [版本号, 2018年12月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Aspect
@Component
public class RunTimeAspect
{
    /**
     * 切入点，方便被通知引用
     */
    @Pointcut("within(com.fly.service..*)")
    public void point()
    {
    }
    
    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint)
        throws Throwable
    {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = new StringBuffer(className).append(".").append(method.getName()).toString();
        HttpServletRequest request = HttpRequestUtils.getHttpServletRequest();
        if (request != null)
        {
            StringBuilder url = new StringBuilder(request.getRequestURI());
            String query = request.getQueryString();
            if (query != null && query.length() > 0)
            {
                url.append("?").append(query);
            }
            log.info("★★★★★★★★ start visit url = {}", url);
        }
        StopWatch clock = new StopWatch();
        clock.start(methodName);
        Object object = joinPoint.proceed();
        clock.stop();
        log.info("★★★★★★★★ running {} ms, method = {}", clock.getTotalTimeMillis(), clock.getLastTaskName());
        return object;
    }
    
    @Before("point()")
    public void before(JoinPoint joinPoint)
    {
        log.info("running @Before");
    }
    
    @After("point()")
    public void after(JoinPoint joinPoint)
    {
        log.info("running @After");
    }
    
    @AfterReturning("point()")
    public void afterReturing(JoinPoint joinPoint)
    {
        log.info("running @AfterReturning");
    }
    
    @AfterThrowing("point()")
    public void afterThrowing(JoinPoint joinPoint)
    {
        log.info("running @AfterThrowing");
    }
}
