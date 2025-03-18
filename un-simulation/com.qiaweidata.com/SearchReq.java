package com.fly.rest.bean;

/**
 * 
 * RequestBody对象
 * 
 * @author 00fly
 * @version [版本号, 2018年11月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SearchReq
{
    Integer pageNo = 1;
    
    Integer pageSize = 10;
    
    String keyword;
    
    public Integer getPageNo()
    {
        return pageNo;
    }
    
    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }
    
    public Integer getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public String getKeyword()
    {
        return keyword;
    }
    
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }
}
