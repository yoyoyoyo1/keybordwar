package com.oracle.demo.service;

import com.oracle.demo.entity.Member;
import org.springframework.stereotype.Service;


public interface MemberService {
    public Member findMemberById(int id);
}