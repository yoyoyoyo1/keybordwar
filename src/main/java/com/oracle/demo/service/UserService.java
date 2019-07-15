package com.oracle.demo.service;

import com.oracle.demo.entity.User;

public interface UserService {
    //插入一个用户
    public User addOne(User user);
    //根据邮箱查询用户(邮箱只能有一个)
    public User findByEmail(String email);
}
