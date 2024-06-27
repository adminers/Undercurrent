package com.fly.simple;

import java.util.Calendar;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainRun2
{
    public static void main(String[] args)
    {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);
        service.scheduleAtFixedRate(() -> {
            int curHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            log.info("========>>>>> huors：");
            IntStream.range(curHour, 24).forEach(System.out::println);
        }, 2, 10, TimeUnit.SECONDS);
    }
}
