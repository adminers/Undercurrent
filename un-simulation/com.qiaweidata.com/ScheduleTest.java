package com.fly.git;

import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScheduleTest
{
    /**
     * 定义运行几次
     */
    int count;
    
    @Test
    public void test()
    {
        try (Scanner sc = new Scanner(System.in))
        {
            do
            {
                mock2();
                log.info("------------输入x退出,回车换行继续------------");
            } while (!"x".equalsIgnoreCase(sc.nextLine()));
            log.info("------------成功退出------------");
        }
    }
    
    /**
     * 模拟测试
     * 
     * @see [类、类#方法、类#成员]
     */
    protected void mock()
    {
        count = RandomUtils.nextInt(1, 8);
        log.info("设定每天至少运行次数： {} ---------", count);
        int endExclusive = 24 / count;
        int randomPoint = RandomUtils.nextInt(0, endExclusive);
        for (int hour = 0; hour < 24; hour++)
        {
            int num = hour % endExclusive;
            if (num == 0)
            {
                log.info("");
            }
            if (num != randomPoint)
            {
                log.info("now {}点：{} != {} ,sleep......", hour, num, randomPoint);
            }
            else
            {
                log.info("now {}点：{} == {} ,run ************", hour, num, randomPoint);
            }
        }
    }
    
    /**
     * 模拟测试
     * 
     * @see [类、类#方法、类#成员]
     */
    protected void mock2()
    {
        count = RandomUtils.nextInt(1, 8);
        log.info("设定每天运行次数： {} ---------", count);
        Set<Integer> hours = new ConcurrentSkipListSet<>();
        while (hours.size() < count)
        {
            hours.add(RandomUtils.nextInt(0, 24));
        }
        log.info("run time hours: {}", hours);
        for (int hour = 0; hour < 24; hour++)
        {
            if (!hours.contains(hour))
            {
                log.info("now {}, sleep......", hour);
                continue;
            }
            log.info("now {}, run **************", hour);
        }
    }
}
