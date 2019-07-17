package com.oracle.demo.service.impl;

import com.oracle.demo.entity.User;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int findIdByEmail(String email)
    {
        return userDao.findIdByEmail(email);
    }
    public List<User> followMeList(List<Integer> followme)
    {
        return userDao.followMe(followme);
    }
    public int changeImg(String name,int id)
    {
        return userDao.changeImg(name,id);
    }
    public User findById(int id)
    {
        return userDao.findById(id);
    }

}
