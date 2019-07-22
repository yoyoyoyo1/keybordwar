package com.oracle.demo.service;

import com.oracle.demo.entity.Info;

import java.util.List;

public interface InfoService {
    public List<Info> getInfos(int userId);
}
