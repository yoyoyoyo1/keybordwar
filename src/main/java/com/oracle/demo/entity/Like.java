package com.example.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Like {
    @Id
    private int userId;//用户id
    private int shareId;//说说id
    private Date createAt;//创建时间
    private int watch;//观看状态


    public Like(){

    }

    public Like(int userId, int shareId, Date createAt, int watch) {
        this.userId = userId;
        this.shareId = shareId;
        this.createAt = createAt;
        this.watch = watch;
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

    @Override
    public String toString() {
        return "Like{" +
                "userId=" + userId +
                ", shareId=" + shareId +
                ", createAt=" + createAt +
                ", watch=" + watch +
                '}';
    }
}
