package com.fly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScannerInput
{
    @Test
    public void test()
        throws Exception
    {
        File file = new File("data-config.ser");
        if (file.exists())
        {
            log.error("****** init from file [{}] success!", file.getAbsolutePath());
        }
        else
        {
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
    
}
