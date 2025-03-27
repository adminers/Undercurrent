package com.fly.simple;

import java.util.Calendar;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleTest
{
    @Test
    public void test()
    {
        int curHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        Assert.assertTrue("check fail", curHour > 0);
        log.info("========>>>>> huors：");
        IntStream.range(curHour, 24).forEach(System.out::println);
    }
}
