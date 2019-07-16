package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Share {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;//文章id
    private int userId;//用户id
    @Column(columnDefinition="TEXT")
    private String content;//文章内容
    @Column(columnDefinition="INT default 0" )
    private int likes;//点赞数
    @Column(columnDefinition="INT default 0" )
    private int comments;//评论数
    @Column(columnDefinition="INT default 0" )
    private int forwards;//转发数
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP" )
    private Date createdAt;//创建时间
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" )
    private Date updatedAt;//修改时间

    public Share(){}

    public Share(int id, int userId, String content, int likes,int comments, int forwards, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.likes = likes;
        this.comments = likes;
        this.forwards = forwards;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getForwards() {
        return forwards;
    }

    public void setForwards(int forwards) {
        this.forwards = forwards;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createAt) {
        this.createdAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updateAt) {
        this.updatedAt = updateAt;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
