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
