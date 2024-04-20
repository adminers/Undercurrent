package com.fly.socket.sample2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * Socket Client
 * 
 * @author 00fly
 * @version [版本号, 2020年6月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyClient
{
    public static void main(String[] args)
    {
        try (Socket socket = new Socket("localhost", 10000))
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true)
            {
                String msg = reader.readLine();
                out.println(msg);
                out.flush();
                if ("bye".equals(msg))
                {
                    break;
                }
                System.out.println(in.readLine());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}