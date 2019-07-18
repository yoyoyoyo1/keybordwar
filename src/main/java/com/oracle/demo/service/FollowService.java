package com.oracle.demo.service;

import java.util.List;

public interface FollowService {
    //根据id查关注数
    public int showFollowing(int id);
    //根据id查被关注数
    public int showFollower(int id);
    //获取自己的关注者列表
    public List<Integer> followMeList(int id);
    //获取自己的关注列表
    public List<Integer> followingList(int id);
}
