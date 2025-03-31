package com.fly.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Executor
{
    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
    
    private static final String OS_NAME = getSystemProperty("os.name");
    
    public static final boolean IS_OS_WINDOWS = getOsMatchesName(OS_NAME_WINDOWS_PREFIX);
    
    /**
     * execute命令
     * 
     * @param command
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static List<String> execute(String[] command)
        throws IOException
    {
        List<String> resultList = new ArrayList<>();
        String[] cmd = IS_OS_WINDOWS ? new String[] {"cmd", "/c", command[0]} : new String[] {"/bin/sh", "-c", command[1]};
        Process ps = Runtime.getRuntime().exec(cmd);
        
        System.out.print("\n ========>>>>> 即将执行命令：");
        Stream.of(cmd).map(y -> y + " ").collect(Collectors.toList()).forEach(System.out::print);
        System.out.println();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream(), "GBK")))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                resultList.add(line);
            }
        }
        return resultList;
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
