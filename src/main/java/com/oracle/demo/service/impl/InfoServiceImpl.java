package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Info;
import com.oracle.demo.respository.InfoDao;
import com.oracle.demo.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InfoServiceImpl implements InfoService {
    @Autowired
    InfoDao infoDao;
    @Override
    public List<Info> getInfos(int userId) {
        return infoDao.getInfos(userId);
    }
}
