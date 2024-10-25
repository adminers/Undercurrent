package com.fly.socket.sample3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClient
{
    private final static Logger logger = Logger.getLogger(MyClient.class.getName());
    
    public static void main(String[] args)
        throws Exception
    {
        for (int i = 0; i < 100; i++)
        {
            ObjectInputStream is = null;
            try (Socket socket = new Socket("localhost", 10000); ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream()))
            {
                User user = new User("user_" + i, "password_" + i);
                os.writeObject(user);
                os.flush();
                is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Object obj = is.readObject();
                if (obj != null)
                {
                    user = (User)obj;
                    System.out.println("user: " + user.getName() + "/" + user.getPassword());
                }
            }
            catch (IOException ex)
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
            }
        }
    }
}