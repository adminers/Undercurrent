package com.fly.socket.sample5;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

public class MyServer
{
    
    private final static Logger logger = Logger.getLogger(MyServer.class.getName());
    
    public static void main(String[] args)
    {
        try
        {
            ServerSocketFactory factory = SSLServerSocketFactory.getDefault();
            ServerSocket server = factory.createServerSocket(10000);
            
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
    
    private static void invoke(final Socket socket)
        throws IOException
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                ObjectInputStream is = null;
                ObjectOutputStream os = null;
                try
                {
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    os = new ObjectOutputStream(socket.getOutputStream());
                    
                    Object obj = is.readObject();
                    User user = (User)obj;
                    System.out.println("user: " + user.getName() + "/" + user.getPassword());
                    
                    user.setName(user.getName() + "_new");
                    user.setPassword(user.getPassword() + "_new");
                    
                    os.writeObject(user);
                    os.flush();
                }
                catch (IOException ex)
                {
                    logger.log(Level.SEVERE, null, ex);
                }
                catch (ClassNotFoundException ex)
                {
                    logger.log(Level.SEVERE, null, ex);
                }
                finally
                {
                    try
                    {
                        is.close();
                    }
                    catch (Exception ex)
                    {
                    }
                    try
                    {
                        os.close();
                    }
                    catch (Exception ex)
                    {
                    }
                    try
                    {
                        socket.close();
                    }
                    catch (Exception ex)
                    {
                    }
                }
            }
        }).start();
    }
}