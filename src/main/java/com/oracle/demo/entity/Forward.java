package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Forward {
    @Id
    private int id;//转发id
    private int shareId;//文章id
    private int userId;//用户id
    private String comments;//转发评论
    private Date createdAt;//转发时间
    private int watch;//观看状态

    public Forward(){}

    public Forward(int id, int shareId, int userId, String comments, Date createdAt, int watch) {
        this.id = id;
        this.shareId = shareId;
        this.userId = userId;
        this.comments = comments;
        this.createdAt = createdAt;
        this.watch = watch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getWatch() {
        return watch;
    }

    public void setWatch(int watch) {
        this.watch = watch;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
