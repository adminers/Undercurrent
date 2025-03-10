package com.fly.hello.simple.input;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScannerInput
{
    @Test
    public void test()
        throws Exception
    {
        File file = new File("data/config.ser");
        if (file.exists())
        {
            log.error("****** init from file [{}] success!", file.getAbsolutePath());
        }
        else
        {
            file.getParentFile().mkdirs();
            log.error("####### init file [{}] not exist!", file.getAbsolutePath());
            log.info("请输入text:");
            try (Scanner sc = new Scanner(System.in))
            {
                String value = sc.nextLine();
                log.info("你输入了{}", value);
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file)))
                {
                    oos.writeObject(value);
                }
                log.info("****** create file [{}] success!", file.getAbsolutePath());
            }
        }
    }
    
    /**
     * 命令行删除目录及其中的所有文件
     * 
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void deleteDirectory()
        throws Exception
    {
        File file = new File("data");
        String localPath = file.getCanonicalPath();
        if (SystemUtils.IS_OS_WINDOWS)
        {
            file.mkdir();
            Runtime.getRuntime().exec("cmd /c start " + localPath);
            TimeUnit.SECONDS.sleep(1);
            Runtime.getRuntime().exec("cmd /c rd /s/q " + localPath);
        }
        if (SystemUtils.IS_OS_UNIX)
        {
            Runtime.getRuntime().exec(new String[] {"/bin/sh", "-c", "rm -rf " + localPath});
        }
    }
    
}
