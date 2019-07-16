package com.oracle.demo.controller;

import com.oracle.demo.entity.Share;
import com.oracle.demo.service.impl.ShareServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ShareController {
    @Autowired
    private ShareServiceImpl shareservice;

    @RequestMapping("/save")
    public String save(String content){
        Share share = new Share();
        share.setContent(content);
        share.setId(5);
        Date date=new Date();
        share.setCreatedAt(date);
        share.setForwards(5);
        share.setLikes(5);
        share.setUserId(5);
        share.setUpdatedAt(date);
        shareservice.save(share);
        return "save successfully!";
    }
}
