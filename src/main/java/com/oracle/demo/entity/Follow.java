package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Follow {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    private int follower;//被关注者
    private int following;//关注者
    public Follow(){}
    public Follow(int id,int follower, int followering) {
        this.id = id;
        this.follower = follower;
        this.following = followering;
    }

    public int getFollower() {
        return follower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
