package com.oracle.demo.controller;

import com.oracle.demo.entity.User;
import com.oracle.demo.util.BeanToMap;
import com.oracle.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
public class file {
    @PostMapping("/api/video/upload")
    public Object upload(HttpSession httpSession,@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println(file);
        User user = (User)httpSession.getAttribute("user");
        String MIME =  file.getOriginalFilename().split("\\.")[1];
        String fileName= MD5Util.encode(user.getId()+file.getOriginalFilename());
        String filePath = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/audio/"+ fileName+"."+MIME;
        System.out.println(filePath);
        file.transferTo(new File(filePath));
        return fileName+"."+MIME;
    }
}
