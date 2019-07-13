package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Likes {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private  int id;
    private int userId;//用户id
    private int shareId;//说说id
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP" )
    private Date createdAt;//创建时间
    @Column(columnDefinition="tinyint default 0")
    private int watch;//观看状态


    public Likes(){

    }

    public Likes(int id,int userId, int shareId, Date createdAt, int watch) {
        this.userId = userId;
        this.id = id;
        this.shareId = shareId;
        this.createdAt = createdAt;
        this.watch = watch;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
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
