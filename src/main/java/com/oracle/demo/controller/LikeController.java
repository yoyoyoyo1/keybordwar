package com.oracle.demo.controller;

import com.oracle.demo.respository.LikeDao;
import com.oracle.demo.service.impl.AddLikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LikeController {

    @Autowired
    AddLikeServiceImpl addLikeService;

    @ResponseBody
    @RequestMapping("/addLike")
    public void toAddLike(int shareId,int userId){
        addLikeService.addLike(shareId, userId);
}
}
