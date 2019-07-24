package com.oracle.demo.service.impl;

import com.oracle.demo.entity.CommentInfo;
import com.oracle.demo.respository.CommentDao;
import com.oracle.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;
    @Override
    public List<CommentInfo> getComment(int shareId){
         return commentDao.findCommentByShareId(shareId);
    }
    @Override
    public int addAComment(String content,int shareId,int userId){
        return commentDao.addComment(content,shareId,userId);

    }
    @Override
    public int updateCommentNum(int shareId){
        return commentDao.updateCommentNum(shareId,shareId);
    }
    @Override
    public List<CommentInfo> getCOC(int shareId){
        return commentDao.findCommentOfCommentByShareId(shareId);
    }
    @Override
    public  int addCOC(int commentId,String content,int shareId,int userId){
        return commentDao.addCommentOfComment(commentId,content,shareId,userId);
    }
    @Override
    public  int findShareId(int id){
        return commentDao.findShareIdById(id);
    }
}
