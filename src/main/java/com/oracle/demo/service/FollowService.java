package com.oracle.demo.service;

public interface FollowService {
    //根据id查关注数
    public int showFollowing(int id);
    //根据id查被关注数
    public int showFollower(int id);
}
