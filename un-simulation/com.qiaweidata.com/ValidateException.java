package com.fly.core.exception;

public class ValidateException extends RuntimeException
{
    private static final long serialVersionUID = -939208231165751812L;
    
    public ValidateException()
    {
        super();
    }
    
    public ValidateException(String message)
    {
        super(message);
    }
}
