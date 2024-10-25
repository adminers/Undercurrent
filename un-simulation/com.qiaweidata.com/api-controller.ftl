package com.fly.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 
 * ${ctrl_desc}Controller
 * 
 * @author 00fly
 * @version [版本号, ${date?date}]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@Api(value = "${ctrl_desc}", description = "${ctrl_desc}")
public class ${ctrl_name}
{      
    <#list models?keys as key>   
    <#assign length=models[key]?length>
    <#assign params=models[key]?split(",")>
    <#assign method=key?replace("/","_")?replace(".","_")>
    
    <#if key?starts_with(ctrl_path)>
    /**
     * ${key}  
     * 
    <#if (length>0)><#list params as param>
     * @param ${param} ${desc[method+'.'+param]}
    </#list></#if>
     * @return String
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "${key}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "${desc[method]}", notes = "${desc[method+'._note']}")
    public String ${method}(
    <#if (length>0)><#list params as param>@RequestParam(required = ${desc[method+'.'+param+'.required']}<#if param?ends_with('appkey')>, defaultValue="34721c5b854ee037ae45442e07681fdf"</#if>)
    @ApiParam(value = "${desc[method+'.'+param]}")
    String ${param}<#if param_has_next>,</#if>
    </#list></#if>)
    {
        return "${key}";
    }
    </#if>
    </#list> 
}
