package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Member;
import com.oracle.demo.respository.MemberDao;
import com.oracle.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao MemberRespository;

    @Override
    public Member findMemberById(int id) throws NoSuchElementException {
        return MemberRespository.findById(id).get();
    }
}
