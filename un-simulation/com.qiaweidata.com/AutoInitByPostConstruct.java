package com.fly.schedule.boot.job;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fly.schedule.boot.util.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AutoInitByPostConstruct
{
    /**
     * PostConstruct注解的方法将会在依赖注入完成后被自动调用<BR>
     * Constructor >> Autowired >> PostConstruct
     */
    @PostConstruct
    private void init()
    {
        try
        {
            String welcome = "@PostConstruct, profile: " + SpringContextUtils.getActiveProfile();
            log.info("---- {}", welcome);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }
}
