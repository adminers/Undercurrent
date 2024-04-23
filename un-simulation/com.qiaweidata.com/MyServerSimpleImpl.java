package com.fly.socket.sample8;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSimpleImpl implements MyServer
{
    
    private int port;
    
    public MyServerSimpleImpl(int port)
    {
        this.port = port;
    }
    
    public void startup()
        throws Exception
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    ServerSocket server = new ServerSocket(port);
                    
                    while (true)
                    {
                        Socket socket = server.accept();
                        invoke(socket);
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    
    @Override
    public void shutdown()
        throws Exception
    {
        // Implement me
    }
    
    private void invoke(final Socket socket)
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                ObjectInputStream ois = null;
                ObjectOutputStream oos = null;
                
                try
                {
                    ois = new ObjectInputStream(socket.getInputStream());
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    
                    Object obj = ois.readObject();
                    MyRequest request = (MyRequest)obj;
                    MyResponse response = execute(request);
                    oos.writeObject(response);
                    oos.flush();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                finally
                {
                    IOUtil.closeQuietly(ois);
                    IOUtil.closeQuietly(oos);
                    IOUtil.closeQuietly(socket);
                }
            }
        }).start();
    }
    
    private MyResponse execute(MyRequest request)
        throws Exception
    {
        Class clazz = request.getRequestClass();
        
        String methodName = request.getRequestMethod();
        Class<?>[] parameterTypes = request.getRequestParameterTypes();
        Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
        
        Object[] parameterValues = request.getRequestParameterValues();
        final Object obj = method.invoke(clazz.newInstance(), parameterValues);
        
        return new MyGenericResponse(obj);
    }
    
}
