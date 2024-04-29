package com.fly.socket.sample8;

import java.util.ArrayList;
import java.util.List;

public class MyUserServiceImpl implements MyUserService
{
    
    @Override
    public List<User> list(int size)
    {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < size; i++)
        {
            users.add(new User("user_" + i, "password_" + i));
        }
        return users;
    }
    
    @Override
    public User findByName(String name)
    {
        return new User(name, null);
    }
    
    @Override
    public void test()
    {
        // do nothing
    }
    
}
