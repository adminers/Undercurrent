package com.fly.core.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fly.core.JsonResult;

import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常处理器
 * 
 * @author 00fly
 * @version [版本号, 2018-09-11]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(value = Exception.class)
    public JsonResult<?> handleBadRequest(Exception exception)
    {
        // JSR303参数校验异常
        if (exception instanceof BindException)
        {
            BindingResult bindingResult = ((BindException)exception).getBindingResult();
            if (null != bindingResult && bindingResult.hasErrors())
            {
                List<String> errMsg = new ArrayList<>();
                bindingResult.getFieldErrors().stream().forEach(fieldError -> {
                    errMsg.add(fieldError.getDefaultMessage());
                });
                Collections.sort(errMsg);
                return JsonResult.error(StringUtils.join(errMsg, ","));
            }
        }
        if (exception instanceof MethodArgumentNotValidException)
        {
            BindingResult bindingResult = ((MethodArgumentNotValidException)exception).getBindingResult();
            if (null != bindingResult && bindingResult.hasErrors())
            {
                List<String> errMsg = new ArrayList<>();
                bindingResult.getFieldErrors().stream().forEach(fieldError -> {
                    errMsg.add(fieldError.getDefaultMessage());
                });
                return JsonResult.error(StringUtils.join(errMsg, ","));
            }
        }
        
        // 手动抛出的ValidateException
        if (exception instanceof ValidateException)
        {
            return JsonResult.error(exception.getMessage());
        }
        
        // 其余情况
        log.error("Error: handleBadRequest StackTrace : {}", exception);
        return JsonResult.error(StringUtils.defaultString(exception.getMessage(), "系统异常，请联系管理员"));
    }
}
