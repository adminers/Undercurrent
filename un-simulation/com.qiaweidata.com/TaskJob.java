package com.fly.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskJob
{
    private static final Logger log = LoggerFactory.getLogger(TaskJob.class);
    
    @Scheduled(fixedDelay = 5000)
    public void retrieveCountry()
    {
        log.info("{}", System.currentTimeMillis());
    }
}
