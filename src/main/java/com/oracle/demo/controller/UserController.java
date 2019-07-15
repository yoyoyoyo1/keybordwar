package com.oracle.demo.controller;

import com.oracle.demo.entity.User;
import com.oracle.demo.respository.UserDao;
import com.oracle.demo.service.UserService;
import com.oracle.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

}
