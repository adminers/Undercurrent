package com.fly.core.utils;

import java.io.IOException;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * xml转换工具
 * 
 * @author 00fly
 *
 */
public class XmlUtils
{
    private static XmlMapper xmlMapper = new XmlMapper();
    
    private static JavaPropsMapper javaPropsMapper = new JavaPropsMapper();
    
    private XmlUtils()
    {
        super();
    }
    
    /**
     * xml转Json字符串
     * 
     * @param xmlContent
     * @return
     * @throws IOException
     */
    public static String xmlToJson(String xmlContent)
        throws IOException
    {
        JsonNode jsonNode = xmlMapper.readTree(xmlContent);
        return jsonNode.toPrettyString();
    }
    

    /**
     * xml转properties
     * 
     * @param xmlContent
     * @return
     * @throws IOException
     */
    public static Properties xmlToProperties(String xmlContent)
        throws IOException
    {
        JsonNode jsonNode = xmlMapper.readTree(xmlContent);
        return javaPropsMapper.writeValueAsProperties(jsonNode);
    }
    
    /**
     * xml转properties字符串
     * 
     * @param xmlContent
     * @return
     * @throws IOException
     */
    public static String xmlToPropText(String xmlContent)
        throws IOException
    {
        JsonNode jsonNode = xmlMapper.readTree(xmlContent);
        return javaPropsMapper.writeValueAsString(jsonNode);
    }
}
