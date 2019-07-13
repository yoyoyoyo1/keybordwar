package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Follow {
    @Id
    private int follower;//被关注者
    private int following;//关注者
    public Follow(){}
    public Follow(int follower, int followering) {
        this.follower = follower;
        this.following = followering;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowering() {
        return following;
    }

    public void setFollowering(int followering) {
        this.following = followering;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
