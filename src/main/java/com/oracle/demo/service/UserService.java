package com.oracle.demo.service;

import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.User;

import java.util.List;

public interface UserService {
    //插入一个用户
    public User addOne(User user);
    //根据邮箱查询用户(邮箱只能有一个)
    public User findByEmail(String email);
    //用户登录
    public User userLogin(String email,String pass);
    //用邮箱查找id(用于follow)
    public int findIdByEmail(String email);
    //用户的关注着列表
    public List<User> followMeList(List<Integer> followme);
    //更换头像
    public int changeImg(String name,int id);
    //根据id查找
    public User findById(int id);
}
