package com.fly.rest.bean;

/**
 * 
 * ErrorModel
 * 
 * @author 00fly
 * @version [版本号, 2018年11月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ErrorModel
{
    private String code;
    
    private String type; // sys bys
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public static ErrorModel sys(String code)
    {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(code);
        errorModel.setType("SYS");
        return errorModel;
    }
    
    public static ErrorModel bys(String code)
    {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(code);
        errorModel.setType("BYS");
        return errorModel;
    }
    
    public static ErrorModel lys(String message)
    {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(message);
        errorModel.setType("LYS");
        return errorModel;
    }
}
