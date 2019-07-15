package com.oracle.demo.service.impl;

import com.oracle.demo.entity.User;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public User addOne(User user)
    {
        return userDao.save(user);
    }
    public User findByEmail(String email)
    {
        return userDao.findByEmail(email);
    }
    public User userLogin(String email,String pass)
    {
        return userDao.userLogin(email,pass);
    }

}
