package com.fly.core.dyn;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * 
 * SqlXmlUtil 工具
 * 
 * @author 00fly
 * @version [版本号, 2018年1月3日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class SqlXmlUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SqlXmlUtil.class);
    
    private Map<String, SQL> mappedStatements = new HashMap<>();
    
    /**
     * 默认构造函数
     * 
     * @throws DocumentException
     */
    public SqlXmlUtil()
    {
        super();
        init();
        if (SystemUtils.IS_OS_WINDOWS)
        {
            // 监测sqlxml变化，方便开发时自测
            monitorStart();
        }
    }
    
    /**
     * 初始化
     * 
     * @see [类、类#方法、类#成员]
     */
    private synchronized void init()
    {
        try
        {
            // 新增sqlxml文件在此添加
            mappedStatements.clear();
            parse("/sqlxml/00fly.xml");
        }
        catch (DocumentException e)
        {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    /**
     * 开始监听
     * 
     * @see [类、类#方法、类#成员]
     */
    private void monitorStart()
    {
        try
        {
            File directory = new File("src/main/resources/sqlxml");
            LOGGER.info("-----monitor start for: {}", directory.getAbsolutePath());
            FileAlterationObserver observer = new FileAlterationObserver(directory, FileFilterUtils.suffixFileFilter(".xml"));
            // 文件监听器
            observer.addListener(new FileAlterationListenerAdaptor()
            {
                @Override
                public void onFileCreate(File file)
                {
                    LOGGER.info("{} created.", file.getName());
                    init();
                }
                
                @Override
                public void onFileChange(File file)
                {
                    LOGGER.info("{} changed.", file.getName());
                    init();
                }
            });
            FileAlterationMonitor monitor = new FileAlterationMonitor(TimeUnit.SECONDS.toMillis(30), observer);
            monitor.start();
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
        }
    }
    
    /**
     * 解析xml文件,DOM4J
     * 
     * @param path
     * @throws DocumentException
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    private void parse(String path)
        throws DocumentException
    {
        LOGGER.info("-----init: {} -------", path);
        URL url = SAXReader.class.getResource(path);
        Document doc = new SAXReader().read(url);
        XPath xPath = doc.createXPath("/sqlMap/sql");
        Element root = doc.getRootElement();
        String name = root.attributeValue("name");
        Assert.hasText(name, "There is NULL name in this SqlMap XML.");
        LOGGER.info("-----success parse name: {} -------", name);
        List<Element> list = xPath.selectNodes(root);
        for (Element element : list)
        {
            // 验证 id
            String id = element.attributeValue("id");
            Assert.hasText(id, "There is NULL sqlId in this SqlMap XML.");
            // 验证 key
            String key = new StringBuilder(name).append(".").append(id).toString();
            if (mappedStatements.containsKey(key))
            {
                throw new IllegalArgumentException("There is already a sqlId named " + id + " in this SqlMap XML.");
            }
            SQL sql = new SQL();
            sql.setMainSql(element.getText());
            List<Element> conditions = element.elements("condition");
            for (Element condition : conditions)
            {
                String conditionId = condition.attributeValue("id");
                String property = condition.attributeValue("property");
                String clause = condition.getTextTrim();
                sql.addCondition(new Condition(conditionId, property, clause));
            }
            mappedStatements.put(key, sql);
            LOGGER.info("-----success add sqlId: {} -------", id);
        }
    }
    
    /**
     * 根据sqlId查询sql语句
     * 
     * @param sqlId
     * @param params 动态条件Map参数
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public String getSqlById(String sqlId, Map<String, Object> params)
    {
        if (!mappedStatements.containsKey(sqlId))
        {
            throw new IllegalArgumentException("There is no sqlId named " + sqlId + " in this SqlMap XML.");
        }
        // 处理动态条件
        SQL sql = mappedStatements.get(sqlId);
        removeNull(params);
        return buildSQLByConditions(sql.getMainSql(), sql.getConditions(), params);
    }
    
    /***
     * 根据condition构造可执行sql
     * 
     * @param sqlText 原始sql
     * @param conditions 动态条件
     * @param params 动态条件Map参数
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String buildSQLByConditions(String sqlText, List<Condition> conditions, Map<String, Object> params)
    {
        for (Condition condition : conditions)
        {
            String id = condition.getId();
            String clause = condition.getClause();
            String property = condition.getProperty();
            if (StringUtils.isNotBlank(property) && params != null)
            {
                Object value = params.get(property);
                if (value instanceof String)
                {
                    if (StringUtils.isNotBlank((String)value))
                    {
                        sqlText = sqlText.replaceAll("@" + id + "\\s", clause + System.getProperty("line.separator"));
                    }
                    else
                    {
                        // 字符串为空
                        sqlText = sqlText.replaceAll("@" + id + "\\s+", "");
                    }
                }
                else
                {
                    if (value != null)
                    {
                        sqlText = sqlText.replaceAll("@" + id + "\\s", clause + System.getProperty("line.separator"));
                    }
                    else
                    {
                        // 动态条件值为null
                        sqlText = sqlText.replaceAll("@" + id + "\\s+", "");
                    }
                }
            }
        }
        return deleteCRLFOnce(sqlText);
    }
    
    /***
     * delete CRLF; delete empty line ;delete blank lines
     * 
     * @param input
     * @return
     */
    private static String deleteCRLFOnce(String input)
    {
        return input.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1");
    }
    
    /**
     * 移除空值
     * 
     * @param params
     * @see [类、类#方法、类#成员]
     */
    private static void removeNull(Map<String, Object> params)
    {
        Iterator<String> it = params.keySet().iterator();
        while (it.hasNext())
        {
            String key = it.next();
            Object value = params.get(key);
            if (value == null || String.class.isInstance(value) && StringUtils.isBlank((String)value) || List.class.isInstance(value) && ((List<?>)value).isEmpty())
            {
                it.remove();
            }
        }
    }
    
    /**
     * 
     * sqlxml文件对应的SQL对象
     * 
     * @author 00fly
     * @version [版本号, 2018年1月5日]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    final class SQL
    {
        /**
         * sql主句
         */
        private String mainSql;
        
        /**
         * 动态条件子句
         */
        private List<Condition> conditions = new ArrayList<>();
        
        public String getMainSql()
        {
            return mainSql;
        }
        
        public void setMainSql(String mainSql)
        {
            this.mainSql = mainSql;
        }
        
        public List<Condition> getConditions()
        {
            return conditions;
        }
        
        public void setConditions(List<Condition> conditions)
        {
            this.conditions = conditions;
        }
        
        /**
         * 添加条件
         * 
         * @param condition 条件
         * @see [类、类#方法、类#成员]
         */
        public void addCondition(Condition condition)
        {
            this.conditions.add(condition);
        }
    }
    
    /**
     * 
     * 动态条件对象
     * 
     * @author 00fly
     * @version [版本号, 2018年1月6日]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    final class Condition
    {
        String id;
        
        String property;
        
        String clause;
        
        public Condition(String id, String property, String clause)
        {
            super();
            this.id = id;
            this.property = property;
            this.clause = clause;
        }
        
        public String getId()
        {
            return id;
        }
        
        public void setId(String id)
        {
            this.id = id;
        }
        
        public String getProperty()
        {
            return property;
        }
        
        public void setProperty(String property)
        {
            this.property = property;
        }
        
        public String getClause()
        {
            return clause;
        }
        
        public void setClause(String clause)
        {
            this.clause = clause;
        }
    }
}
