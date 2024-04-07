package com.fly.log;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MongDbRun
{
    public static void main(String[] args)
    {
        // 随机
        if (new Random().nextBoolean())
        {
            ScheduledExecutorService service = new ScheduledThreadPoolExecutor(2);
            service.scheduleAtFixedRate(() -> {
                log.info("schedule: logger mongdb at {}", System.currentTimeMillis());
            }, 2, 5, TimeUnit.SECONDS);
            return;
        }
        
        try (Scanner sc = new Scanner(System.in))
        {
            do
            {
                long t = System.currentTimeMillis();
                String time = DateFormatUtils.format(t, "yyyy-MM-dd HH:mm:ss");
                for (int i = 0; i < 10; i++)
                {
                    log.info("logger mongdb {} at {}", t++, time);
                }
                log.info("------------输入x退出,回车换行继续------------");
            } while (!"x".equalsIgnoreCase(sc.nextLine()));
            log.info("------------成功退出------------");
        }
    }
}