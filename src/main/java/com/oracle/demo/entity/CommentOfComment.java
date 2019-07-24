package com.oracle.demo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CommentOfComment {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    private int userId;//评论人id
    private int shareId;//说说id
    private int commentId;//评论id
    @Column(columnDefinition="TEXT")
    private String content;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP" )
    private Date createdAt;


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

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
