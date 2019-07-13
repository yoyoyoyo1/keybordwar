package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dialog {
    @Id
    private int id;//圆桌号
    private String title;//标题
    private String content;//补充说明

    public Dialog(){}

    public Dialog(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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
