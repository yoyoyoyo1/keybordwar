package com.oracle.demo.controller;

import com.oracle.demo.entity.CommentInfo;
import com.oracle.demo.entity.User;
import com.oracle.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    @RequestMapping(value="/getCOC")
    @ResponseBody
    public List<CommentInfo> openCOC(int i){
        System.out.println("-----"+i);
        List<CommentInfo> coc=commentService.getCOC(i);
        return coc;
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
    @RequestMapping(value="/sendCOC")
    @ResponseBody
    public int sendCOC(int commentId,String content,int shareId,HttpSession session){
        System.out.println("cococococococo");
        User user=(User)session.getAttribute("user");
        int userId=user.getId();
        int i=commentService.addCOC(commentId,content,shareId,userId);
        System.out.println("楼中楼发送");
        return i;
    }
    @RequestMapping(value="/getShareId")
    @ResponseBody
    public int getShareId(int commentId){
        int shareId=commentService.findShareId(commentId);
        return shareId;
    }

}
