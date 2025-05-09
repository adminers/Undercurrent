package com.fly.schedule.boot.job;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.fly.schedule.boot.util.SpringContextUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Import(SpringContextUtils.class)
public class InitByImport
{
    /**
     * PostConstruct注解的方法将会在依赖注入完成后被自动调用<BR>
     * Constructor >> Autowired >> PostConstruct
     */
    @PostConstruct
    private void init()
    {
        String welcome = "@Import(SpringContextUtils.class), profile: " + SpringContextUtils.getActiveProfile();
        log.info("---- {}", welcome);
    }
}
