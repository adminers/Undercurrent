package com.fly.core.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesUtilsTest
{
    String yamlContent;
    
    @Before
    public void init()
    {
        try
        {
            Resource resource = new ClassPathResource("application.yml");
            yamlContent = FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8.toString());
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void propertiesToJson()
        throws IOException
    {
        Properties properties = YamlUtils.yamlToProperties(yamlContent);
        String json = PropertiesUtils.propertiesToJson(properties);
        log.info("Properties: \n{}", properties);
        log.info("propertiesToJson: \n{}", json);
    }
    
    @Test
    public void propertiesToYaml()
        throws IOException
    {
        Properties properties = YamlUtils.yamlToProperties(yamlContent);
        String yaml = PropertiesUtils.propertiesToYaml(properties);
        log.info("Properties: \n{}", properties);
        log.info("propertiesToYaml: \n{}", yaml);
    }
    
    @Test
    public void propTextToJson()
        throws IOException
    {
        String propText = YamlUtils.yamlToPropText(yamlContent);
        String json = PropertiesUtils.propTextToJson(propText);
        log.info("propText: \n{}", propText);
        log.info("propTextToJson: \n{}", json);
    }
    
    @Test
    public void propTextToYaml()
        throws IOException
    {
        String propText = YamlUtils.yamlToPropText(yamlContent);
        String yaml = PropertiesUtils.propTextToYaml(propText);
        log.info("propText: \n{}", propText);
        log.info("propTextToYaml: \n{}", yaml);
    }
}
