package com.fly.schedule.simple.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Executor
{
    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
    
    private static final String OS_NAME = getSystemProperty("os.name");
    
    public static final boolean IS_OS_WINDOWS = getOsMatchesName(OS_NAME_WINDOWS_PREFIX);
    
    /**
     * 执行命令
     * 
     * @param command
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static void execute(String command)
        throws IOException
    {
        // 执行结果写入execute.log
        File file = new File("execute.log");
        try (FileWriter fileWritter = new FileWriter(file.getName(), true))
        {
            String[] cmd = IS_OS_WINDOWS ? new String[] {"cmd", "/c", command} : new String[] {"/bin/sh", "-c", command};
            Process ps = Runtime.getRuntime().exec(cmd);
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
            {
                fileWritter.write(line);
                fileWritter.write("\n");
            }
        }
    }
    
    private static boolean getOsMatchesName(final String osNamePrefix)
    {
        return isOSNameMatch(OS_NAME, osNamePrefix);
    }
    
    private static String getSystemProperty(final String property)
    {
        try
        {
            return System.getProperty(property);
        }
        catch (SecurityException ex)
        {
            return null;
        }
    }
    
    private static boolean isOSNameMatch(String osName, String osNamePrefix)
    {
        if (osName == null)
        {
            return false;
        }
        return osName.startsWith(osNamePrefix);
    }
}
