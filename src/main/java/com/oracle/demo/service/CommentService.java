package com.oracle.demo.service;

import com.oracle.demo.entity.CommentInfo;

import java.util.List;
import java.util.Map;

public interface CommentService {
    public List<CommentInfo> getComment(int shareId);
    public int addAComment(String content,int shareId,int userId);
    public int updateCommentNum(int shaerId);
    public List<CommentInfo> getCOC(int shareId);
    public int addCOC(int commentId,String content,int shareId,int userId);
    public int findShareId(int id);
}
