package com.fly.core.entity;

/**
 * 
 * 表字段信息
 * 
 * @author 00fly
 * @version [版本号, 2017-8-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Column
{
    /**
     * 字段名
     */
    private String name;
    
    /**
     * 字段数据类型
     */
    private String sqlType;
    
    /**
     * 注释
     */
    private String comment;
    
    /**
     * 主键字段标识
     */
    private boolean primaryKey = false;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSqlType()
    {
        return sqlType;
    }
    
    public void setSqlType(String sqlType)
    {
        this.sqlType = sqlType;
    }
    
    public String getComment()
    {
        return comment;
    }
    
    public void setComment(String comment)
    {
        this.comment = comment;
    }
    
    public boolean isPrimaryKey()
    {
        return primaryKey;
    }
    
    public void setPrimaryKey(boolean primaryKey)
    {
        this.primaryKey = primaryKey;
    }
}
