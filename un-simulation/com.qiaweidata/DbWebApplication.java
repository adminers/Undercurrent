package com.fly;

import java.lang.management.ManagementFactory;
import java.util.Iterator;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DbWebApplication implements CommandLineRunner
{
    @Value("${server.port}")
    String serverPort;
    
    public static void main(String[] args)
    {
        SpringApplication.run(DbWebApplication.class, args);
    }
    
    /**
     * 获取tomcat启动端口
     * 
     * @return
     * @throws MalformedObjectNameException
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String getTomcatPort()
        throws MalformedObjectNameException
    {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"), Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
        for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();)
        {
            ObjectName obj = i.next();
            String port = obj.getKeyProperty("port");
            if (StringUtils.isNotBlank(port))
            {
                return port;
            }
        }
        // 偷懒，非tomcat服务器统一返回8080
        return "8080";
    }
    
    @Override
    public void run(String... args)
        throws Exception
    {
        if (SystemUtils.IS_OS_WINDOWS)
        {
            Runtime.getRuntime().exec("cmd.exe /c start /min http://127.0.0.1:" + serverPort);
        }
    }
}