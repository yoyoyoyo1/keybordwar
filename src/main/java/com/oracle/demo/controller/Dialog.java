package com.oracle.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dialog")
public class Dialog {
    @GetMapping("/user")
    public Object user(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",0);
        map.put("nickname",123);
        map.put("motto","asd");
        list.add(map);
        return list;
    }


}
