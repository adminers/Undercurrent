<%@page import="com.fly.api.utils.JsonUtil"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.*"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    String param1 = request.getParameter("param1");
    String param2 = request.getParameter("param2");
    String param3 = request.getParameter("param3");
    String param4 = request.getParameter("param4");
    
    Map<String, Object> map = new HashMap<String, Object>();
    if (StringUtils.isAnyBlank(param1, param2, param3, param4))
    {
        map.put("result", "1000");
        map.put("msg", "必选参数取值错误或缺失");
        out.print(JsonUtil.mapToJson(map));
        return;
    }
    map.put("result", "0000");
    map.put("msg", "参数合法");
    out.print(JsonUtil.mapToJson(map));
%>