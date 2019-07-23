package com.oracle.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CommentOfComment {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    private int userId;//评论人id
    private int shareId;//说说id
    private int commentId;

}
