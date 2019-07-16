package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Share;
import com.oracle.demo.respository.ShareDao;
import com.oracle.demo.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareDao shareRepository;

    @Override
    public List<Share> getAll() {
        return shareRepository.findAll();
    }

}