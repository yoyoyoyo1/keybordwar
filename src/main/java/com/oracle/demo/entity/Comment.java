package com.oracle.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Comment {
    @Id
    private int userId;//评论人id
    private int shareId;//说说id
    private String content;//评论内容
    private Date createAt;//创建时间
    private int watch;//观看状态
    private int id;//评论id

    public Comment(){}

    public Comment(int userId, int shareId, String content, Date createAt, int watch, int id) {
        this.userId = userId;
        this.shareId = shareId;
        this.content = content;
        this.createAt = createAt;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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
        return "Comment{" +
                "userId=" + userId +
                ", shareId=" + shareId +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", watch=" + watch +
                ", id=" + id +
                '}';
    }
}
