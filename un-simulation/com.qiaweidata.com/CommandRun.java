package com.fly.hello.simple.input;

import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 命令行交互方式运行
 * 
 * @author 00fly
 * @version [版本号, 2016-12-10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
public class CommandRun
{
    static Scanner sc;
    
    @Test
    public void test()
    {
        sc = new Scanner(System.in);
        String tableName = getInput("表名");
        String packName = getInput("包路径");
        String className = getInput("类名");
        log.info("请确认以下信息：");
        log.info("1.数据库表{}", tableName);
        log.info("2.包路径：{}", packName);
        log.info("3.类名：{}", className);
        log.info("回车继续,其他取消");
        
        if (StringUtils.isEmpty(sc.nextLine()))
        {
            // 开始运行
            log.info("------------开始运行------------");
            log.info("------------运行结束------------");
        }
        else
        {
            log.info("------------取消运行成功------------");
        }
        sc.close();
    }
    
    /**
     * getInput
     * 
     * @param msg 提示语
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String getInput(String msg)
    {
        String value;
        do
        {
            log.info("请输入{}", msg);
            value = sc.nextLine();
        } while (StringUtils.isEmpty(value));
        log.info("你输入了{}：{}", msg, value);
        return value;
    }
}