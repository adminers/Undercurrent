package com.fly.socket.sample8;

public class MyGenericResponseHandler<T> implements MyResponseHandler<T>
{
    @Override
    public T handle(MyResponse response)
    {
        return (T)response.getResult();
    }
}
