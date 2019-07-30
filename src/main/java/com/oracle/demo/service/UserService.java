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
    public List<User> findByIds(List<Integer> ids);
    public User findById(int id);
    //修改签名和昵称
    public int updateInfo(String nickname,String motto,int id);
    //修改用户密码
    public int updatePwd(String pass,int id);
    //忘记用户密码
    public int changePwd(String pass,String email);
    //根据用户邮箱和电话查询
    public User findByEandP(String email,String phone);
}
