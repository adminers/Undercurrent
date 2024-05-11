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
 * API模块3Controller
 * 
 * @author 00fly
 * @version [版本号, 2018-9-19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@Api(value = "API模块3", description = "API模块3")
public class V3Controller
{
    
    /**
     * v3/update.jsp
     * 
     * @param param1 param1
     * @param param2 param2
     * @param param3 param3
     * @param param4 param4
     * @return String
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "v3/update.jsp", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "v3/update.jsp", notes = "v3/update.jsp详细说明")
    public String v3_update_jsp(@RequestParam(required = true) @ApiParam(value = "param1") String param1, @RequestParam(required = true) @ApiParam(value = "param2") String param2,
        @RequestParam(required = true) @ApiParam(value = "param3") String param3, @RequestParam(required = true) @ApiParam(value = "param4") String param4)
    {
        return "v3/update.jsp";
    }
    
    /**
     * v3/delete.jsp
     * 
     * @param param1 param1
     * @param param2 param2
     * @param param3 param3
     * @param param4 param4
     * @return String
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "v3/delete.jsp", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "v3/delete.jsp", notes = "v3/delete.jsp详细说明")
    public String v3_delete_jsp(@RequestParam(required = true) @ApiParam(value = "param1") String param1, @RequestParam(required = true) @ApiParam(value = "param2") String param2,
        @RequestParam(required = true) @ApiParam(value = "param3") String param3, @RequestParam(required = true) @ApiParam(value = "param4") String param4)
    {
        return "v3/delete.jsp";
    }
    
    /**
     * v3/query.jsp
     * 
     * @param param1 param1
     * @param param2 param2
     * @param param3 param3
     * @param param4 param4
     * @return String
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "v3/query.jsp", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "v3/query.jsp", notes = "v3/query.jsp详细说明")
    public String v3_query_jsp(@RequestParam(required = true) @ApiParam(value = "param1") String param1, @RequestParam(required = true) @ApiParam(value = "param2") String param2,
        @RequestParam(required = true) @ApiParam(value = "param3") String param3, @RequestParam(required = true) @ApiParam(value = "param4") String param4)
    {
        return "v3/query.jsp";
    }
    
    /**
     * v3/insert.jsp
     * 
     * @param param1 param1
     * @param param2 param2
     * @param param3 param3
     * @param param4 param4
     * @return String
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "v3/insert.jsp", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "v3/insert.jsp", notes = "v3/insert.jsp详细说明")
    public String v3_insert_jsp(@RequestParam(required = true) @ApiParam(value = "param1") String param1, @RequestParam(required = true) @ApiParam(value = "param2") String param2,
        @RequestParam(required = true) @ApiParam(value = "param3") String param3, @RequestParam(required = true) @ApiParam(value = "param4") String param4)
    {
        return "v3/insert.jsp";
    }
}
