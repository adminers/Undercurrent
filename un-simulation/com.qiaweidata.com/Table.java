package com.fly.core.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 表实体对象
 * 
 * @author 00fly
 * @version [版本号, 2017-8-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Table
{
    /**
     * 表名
     */
    private String name;
    
    /**
     * 表注释
     */
    private String comment;
    
    /**
     * 数据来源
     */
    private String[] sources = new String[] {};
    
    /**
     * 表字段信息
     */
    private List<Column> columns = new ArrayList<>();
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getComment()
    {
        return comment;
    }
    
    public void setComment(String comment)
    {
        this.comment = comment;
    }
    
    public String[] getSources()
    {
        return sources;
    }
    
    public void setSources(String[] sources)
    {
        this.sources = sources;
    }
    
    public List<Column> getColumns()
    {
        return columns;
    }
    
    public void setColumns(List<Column> columns)
    {
        this.columns = columns;
    }
    
    public void addColumn(Column column)
    {
        this.columns.add(column);
    }
}
