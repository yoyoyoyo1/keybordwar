package com.oracle.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Share {
    @Id
    private int id;//文章id
    private int userId;//用户id
    private String content;//文章内容
    private int likes;//点赞数
    private int forwards;//转发数
    private Date createAt;//创建时间
    private Date updateAt;//修改时间

    public Share(){}

    public Share(int id, int userId, String content, int likes, int forwards, Date createAt, Date updateAt) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.likes = likes;
        this.forwards = forwards;
        this.createAt = createAt;
        this.updateAt = updateAt;
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

    public int getForwards() {
        return forwards;
    }

    public void setForwards(int forwards) {
        this.forwards = forwards;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Share{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", forwards=" + forwards +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
