package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Share;
import com.oracle.demo.respository.ShareDao;
import com.oracle.demo.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    private ShareDao dao;
    @Override
    public void save(Share share){
        dao.save(share);
    }

}
