package com.oracle.demo.service;

import com.oracle.demo.entity.User;

public interface UserService {
    //插入一个用户
    public User addOne(User user);
    //根据邮箱查询用户(邮箱只能有一个)
    public User findByEmail(String email);
    //用户登录
    public User userLogin(String email,String pass);
    //用邮箱查找id(用于follow)
    public int findIdByEmail(String email);
}
