package com.fly.core.auth;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fly.git.service.GitClientService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fly.core.entity.JsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * AuthInterceptor
 * 
 * @author 00fly
 * @version [版本号, 2019年7月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter
{
    /**
     * token
     */
    private String token = null;
    
    /**
     * token 生成时间
     */
    private Date tokenTime;
    
    @Value("${white.list:127.0.0.1}")
    private String whiteList;
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception
    {
        String ip = request.getRemoteAddr();
        String token = request.getHeader("token");
        log.info("★★★★★★★★ request ip: {}, head token : {}", ip, token);
        if ("POST".equals(request.getMethod()) && !StringUtils.contains(whiteList, ip) && !StringUtils.equals(token, getToken()))
        {
            JsonResult<?> result = JsonResult.error("禁止访问，请添加白名单IP：" + ip + "或设置合法token");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().print(mapper.writeValueAsString(result));
            return false;
        }
        return true;
    }
    
    /**
     * 获取token
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private synchronized String getToken()
    {

        /*Date now = new Date();
        boolean valide = token != null && tokenTime != null && now.before(DateUtils.addMinutes(tokenTime, 30));
        if (!valide)
        {
            token = UUID.randomUUID().toString().replace("-", "");
            tokenTime = now;
        }*/
        token = GitClientService.properties.get("gitToken");
        log.info("------ now valid token is： {}", token);
        return token;
    }
}
