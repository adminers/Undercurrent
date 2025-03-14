package com.fly;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainRun
{
    private static final Logger logger = LoggerFactory.getLogger(MainRun.class);
    
    public void test()
    {
        try (Scanner sc = new Scanner(System.in))
        {
            do
            {
                
                logger.info("------------输入x退出,回车换行继续------------");
            } while (!StringUtils.equalsIgnoreCase(sc.nextLine(), "x"));
            logger.info("------------成功退出------------");
        }
    }
    
}
