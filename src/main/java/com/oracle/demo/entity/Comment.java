package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;//评论id
    private int userId;//评论人id
    private int shareId;//说说id
    @Column(columnDefinition="TEXT")
    private String content;//评论内容
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP" )
    private Date createdAt;//创建时间
    @Column(columnDefinition="tinyint default 0")
    private int watch;//观看状态

    public Comment(){}

    public Comment(int userId, int shareId, String content, Date createdAt, int watch, int id) {
        this.userId = userId;
        this.shareId = shareId;
        this.content = content;
        this.createdAt = createdAt;
        this.watch = watch;
        this.id = id;
    }

    public int getUserId() {
        return userId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreateAt(Date createAt) {
        this.createdAt = createdAt;
    }

    public int getWatch() {
        return watch;
    }

    public void setWatch(int watch) {
        this.watch = watch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
