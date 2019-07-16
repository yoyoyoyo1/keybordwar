package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;

@Entity
public class Dialog {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;//圆桌号
    @Column(length=20)
    private String title;//标题
    private String content;//补充说明
    private String image;//用户的头像地址
    @Column(columnDefinition="int default 0")
    private int likes;
    @Column(columnDefinition="tinyint default 1")
    private int active;

    public Dialog(){}

    public Dialog(int id, String title, String content,String image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
