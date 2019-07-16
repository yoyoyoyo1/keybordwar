package com.oracle.demo.controller;


import com.oracle.demo.entity.Share;
import com.oracle.demo.service.impl.ShareServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class ShareController {
    @Autowired
    private ShareServiceImpl shareService;



    @RequestMapping("/index")
    public String toIndex(Model model){
        List<Share> shareList=shareService.getAll();
        model.addAttribute("shareList",shareList);
        return "index";
    }


    }
