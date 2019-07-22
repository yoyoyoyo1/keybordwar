package com.oracle.demo.entity;

import com.alibaba.fastjson.JSON;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Info {
    @Id
    private String id;
    private Integer userId;
    private String type;//"like forward share"
    private Integer shareId;
    private Date createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
            this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Integer getShareId() {
        return shareId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setShareId(Integer shareId) {
        this.shareId = shareId;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
