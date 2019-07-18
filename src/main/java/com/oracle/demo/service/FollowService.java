package com.oracle.demo.service;

import com.oracle.demo.entity.Follow;

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
    //查看是否关注过他
    public Follow isFollow(int follower,int following);
    //关注某人
    public Follow doFollow(Follow follow);
    //取关某人
    public int undoFollow(int follower,int following);
}
