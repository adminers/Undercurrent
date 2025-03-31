package com.fly;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

import com.fly.reload.RedisConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ReloadApplication implements CommandLineRunner
{
    @Autowired
    RedisConfig redisConfig;
    
    @Override
    public void run(String... args)
        throws Exception
    {
        try (Scanner sc = new Scanner(System.in))
        {
            String input;
            do
            {
                log.info("RedisConfig port={}", redisConfig.getPort());
                log.info("------------输入q退出,输入其他值继续运行------------");
                input = StringUtils.trimAllWhitespace(sc.nextLine());
            } while (!input.equalsIgnoreCase("q"));
            log.info("----------系统退出成功----------");
        }
    }
    
    /**
     * 启动 SpringBootApplication
     * 
     * @param args
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
        throws IOException
    {
        SpringApplication.run(ReloadApplication.class);
    }
}