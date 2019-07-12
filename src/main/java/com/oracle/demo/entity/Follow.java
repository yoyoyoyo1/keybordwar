package com.oracle.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Follow {
    @Id
    private int follower;//被关注者
    private int followering;//关注者
    public Follow(){}
    public Follow(int follower, int followering) {
        this.follower = follower;
        this.followering = followering;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getFollowering() {
        return followering;
    }

    public void setFollowering(int followering) {
        this.followering = followering;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "follower=" + follower +
                ", followering=" + followering +
                '}';
    }
}
