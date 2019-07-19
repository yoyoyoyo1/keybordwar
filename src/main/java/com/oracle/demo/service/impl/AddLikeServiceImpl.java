package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Likes;
import com.oracle.demo.respository.LikeDao;
import com.oracle.demo.service.AddLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddLikeServiceImpl {
    @Autowired
    LikeDao likeDao;
    public void addLike(int shareId,int userId){
//判定是否点赞，没点点赞数加1，点了就取消点赞
       if (likeDao.findLike(shareId, userId)==null){
           System.out.println("未点过赞");
           likeDao.addALike(shareId, userId);

       }
       else{
           System.out.println("已点过赞");
           likeDao.deleteLike(shareId, userId);
       }
        int a=likeDao.findLikeNum(shareId);
        likeDao.updateLikes(a,shareId);
        System.out.println("更新了"+shareId+"的点赞数为"+a);

    }
}
