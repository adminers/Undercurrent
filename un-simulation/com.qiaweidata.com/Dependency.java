package com.fly.sample.bean;

/**
 * 
 * 一句话功能简述> 功能详细描述>
 * 
 * @author ronghuikj
 * @version [版本号, 2021年2月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class Dependency
{
    private String groupId;
    
    private String artifactId;
    
    private String version;
    
    private String scope;
    
    private String systemPath;
    
    public String getGroupId()
    {
        return groupId;
    }
    
    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }
    
    public String getArtifactId()
    {
        return artifactId;
    }
    
    public void setArtifactId(String artifactId)
    {
        this.artifactId = artifactId;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public String getScope()
    {
        return scope;
    }
    
    public void setScope(String scope)
    {
        this.scope = scope;
    }
    
    public String getSystemPath()
    {
        return systemPath;
    }
    
    public void setSystemPath(String systemPath)
    {
        this.systemPath = systemPath;
    }
    
}
