package com.fly.core.log;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.fly.core.utils.YamlUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 日志数据库数据源
 * 
 * @author 00fly
 * @version [版本号, 2023年3月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
public final class LogPoolManager
{
    private static DataSource dataSource;
    
    private LogPoolManager()
    {
        super();
    }
    
    /**
     * boot启动时指定的外部配置文件位置
     */
    private static String configLocation;
    
    public static void setConfigLocation(String configLocation)
    {
        LogPoolManager.configLocation = configLocation;
    }
    
    /**
     * 不能静态初始化 DataSource，否则无法加载外部配置文件
     */
    public static synchronized void init()
    {
        try
        {
            // 加载外部配置文件
            if (StringUtils.isNotBlank(configLocation))
            {
                File file = new File(configLocation);
                String text = FileUtils.readFileToString(file, StandardCharsets.UTF_8.toString());
                Properties props = YamlUtils.yamlToProperties(text);
                dataSource = DataSourceBuilder.create()
                    .type(DruidDataSource.class)
                    .url(props.getProperty("spring.datasource.url"))
                    .username(props.getProperty("spring.datasource.username"))
                    .password(props.getProperty("spring.datasource.password"))
                    .build();
            }
            else
            {
                
                Resource resource = new ClassPathResource("application.yml");
                String text = IOUtils.toString(resource.getURL(), StandardCharsets.UTF_8.toString());
                boolean dev = StringUtils.contains(text, "dev");
                log.info("#### DruidDataSource init ####");
                Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(dev ? "jdbc-h2.properties" : "jdbc-mysql.properties"));
                dataSource = DruidDataSourceFactory.createDataSource(properties);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }
    
    /**
     * getConnection
     * 
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public static Connection getConnection()
        throws SQLException
    {
        if (dataSource == null)
        {
            init();
        }
        Assert.notNull(dataSource, "dataSource is null");
        return dataSource.getConnection();
    }
}
