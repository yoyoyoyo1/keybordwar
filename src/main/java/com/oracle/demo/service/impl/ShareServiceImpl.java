package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Share;
import com.oracle.demo.respository.ShareDao;
import com.oracle.demo.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    ShareDao shareDao;
    public Share sendShare(Share share)
    {
        return shareDao.save(share);
    }
}
