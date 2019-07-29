package com.oracle.demo.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
public class CommentInfo {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(length=20)
    private String nickname;
    private String image;
    private int cocNeedId;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getCocNeedId() {
        return cocNeedId;
    }

    public void setCocNeedId(int cocNeedId) {
        this.cocNeedId = cocNeedId;
    }
}
