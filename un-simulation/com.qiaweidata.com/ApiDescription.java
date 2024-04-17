package com.mangofactory.swagger.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"path", "description", "hidden", "operations"})
public class ApiDescription
{
    private final String path;
    
    private final String description;
    
    private final List<Operation> operations;
    
    @JsonIgnore
    private final Boolean hidden;
    
    public ApiDescription(String path, String description, List<Operation> operations, Boolean hidden)
    {
        // 在web.xml中url-pattern设置为/api/*,故此处做特殊处理
        this.path = "/api" + path;
        this.description = description;
        this.operations = operations;
        this.hidden = hidden;
    }
    
    public String getPath()
    {
        return this.path;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public List<Operation> getOperations()
    {
        return this.operations;
    }
    
    public Boolean isHidden()
    {
        return this.hidden;
    }
}
