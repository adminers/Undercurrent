package com.fly.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * 结果对象
 * 
 * @author 00fly
 * @version [版本号, 2021年5月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@ApiModel(description = "Json格式消息体")
public class JsonResult<T>
{
    @ApiModelProperty(value = "数据对象")
    private T data;
    
    @ApiModelProperty(value = "是否成功", required = true, example = "true")
    private boolean success;
    
    @ApiModelProperty(value = "错误码")
    private String errorCode;
    
    @ApiModelProperty(value = "提示信息")
    private String message;
    
    public JsonResult()
    {
        super();
    }
    
    public static <T> JsonResult<T> success(T data)
    {
        JsonResult<T> r = new JsonResult<>();
        r.setData(data);
        r.setSuccess(true);
        return r;
    }
    
    public static JsonResult<?> success()
    {
        JsonResult<Object> r = new JsonResult<>();
        r.setSuccess(true);
        return r;
    }
    
    public static JsonResult<Object> error(String code, String msg)
    {
        JsonResult<Object> r = new JsonResult<>();
        r.setSuccess(false);
        r.setErrorCode(code);
        r.setMessage(msg);
        return r;
    }
    
    public static JsonResult<Object> error(String msg)
    {
        return error("500", msg);
    }
}
