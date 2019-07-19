package com.oracle.demo.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
@Entity
@DynamicInsert
@DynamicUpdate
public class ShareInfo {
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
    private int likeInfo;
    @Column(columnDefinition="INT default 0" )
    private int forwards;//转发数
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP" )
    private Date createdAt;//创建时间
    @Column(length=20)
    private String nickname;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public  int getUserId(){return userId;}

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

    public int getLikeInfo() {
        return likeInfo;
    }

    public void setLikeInfo(int likeInfo) {
        this.likeInfo = likeInfo;
    }

}
