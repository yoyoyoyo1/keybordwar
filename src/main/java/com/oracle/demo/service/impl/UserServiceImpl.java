package com.oracle.demo.service.impl;

import com.oracle.demo.entity.User;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.cs.US_ASCII;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public int addOne(User user)
    {
        return userDao.addOne(user.getEmail(),user.getPass(),user.getPhone(),user.getNickname());
    }

}
