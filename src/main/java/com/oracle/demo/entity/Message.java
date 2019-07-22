package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;//消息id
    private int formId;//消息来源（用户id）
    private int toId;//消息的去向（用户或圆桌id）
    @Column(length=20)
    private String type;
    @Column(columnDefinition="TEXT")
    private String content;//（内容）
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP" )
    private Date    createAt;//（创建时间）
    @Column(columnDefinition="tinyint default 0")
    private int watch;//0表示未看，1表示已看，2表示圆桌话题
    public Message(){}

    public Message(int id, int formId, int toId, String content, Date createAt, int watch) {
        this.id = id;
        this.formId = formId;
        this.toId = toId;
        this.content = content;
        this.createAt = createAt;
        this.watch = watch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
