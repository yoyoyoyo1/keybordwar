package com.oracle.demo.service;

import com.oracle.demo.entity.CommentInfo;

import java.util.List;
import java.util.Map;

public interface CommentService {
    public List<CommentInfo> getComment(int shareId);
    public int addAComment(String content,int shareId,int userId);
    public int updateCommentNum(int shaerId);
}
