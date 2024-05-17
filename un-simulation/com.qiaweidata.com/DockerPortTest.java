package com.fly.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DockerPortTest
{
    
    /**
     * 输出docker应用映射端口
     * 
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void printPorts()
        throws IOException
    {
        String[] cmd = SystemUtils.IS_OS_WINDOWS ? new String[] {"cmd", "/c", "docker ps --format \"{{.Names}} {{.Ports}}\""}
            : new String[] {"/bin/sh", "-c", "docker ps --format \"{{.Names}} {{.Ports}}\""};
        Process ps = Runtime.getRuntime().exec(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
        Map<String, Set<String>> map = new TreeMap<>();
        String line;
        while ((line = br.readLine()) != null)
        {
            log.info("{}", line);
            String name = StringUtils.substringBefore(line, " ");
            Set<String> ports = new TreeSet<>();
            String portText = StringUtils.substringAfter(line, " ");
            for (String it : portText.split(","))
            {
                String port = StringUtils.substringBetween(it, ":", "->");
                if (StringUtils.isNotBlank(port))
                {
                    ports.add(port.replace(":", ""));
                }
            }
            map.put(name, ports);
        }
        log.info("{}", map);
    }
}
