package com.fly.cache.service;

import java.util.List;

import com.fly.cache.model.User;

public interface IUserService
{
    void saveOrUpdate(User user);
    
    void delete(Long userId);
    
    User get(Long userId);
    
    List<User> list();
    
}
