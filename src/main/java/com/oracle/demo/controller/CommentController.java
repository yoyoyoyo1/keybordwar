package com.oracle.demo.controller;

import com.oracle.demo.entity.CommentInfo;
import com.oracle.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping(value="/getComment")
    @ResponseBody
    public List<CommentInfo> openComment(int i){
        System.out.println("-----"+i);
        List<CommentInfo> comments=commentService.getComment(i);
        return comments;
    }
    @RequestMapping(value="/sendComment")
    @ResponseBody
    public int sendComment(String content,int shareId,int userId){
        System.out.println("动态评论"+shareId);
        int i=commentService.addAComment(content,shareId,userId);
        int a=commentService.updateCommentNum(shareId);
        System.out.println(i+"条评论已发送于"+shareId+"---"+a);
        return i;
    }
}
