package com.oracle.demo.web;

import com.oracle.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pjj")
public class pjj {
    @Autowired
    MemberService memberService;
    @RequestMapping("/123")
    public Object member(){
        return memberService.findMemberById(123);
    }

}
