package com.fly.socket.sample8;

import java.io.Serializable;

public interface MyRequest extends Serializable
{
    
    Class<?> getRequestClass();
    
    String getRequestMethod();
    
    Class<?>[] getRequestParameterTypes();
    
    Object[] getRequestParameterValues();
    
}
