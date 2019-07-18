package com.oracle.demo.service;

import com.oracle.demo.entity.Share;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ShareService {
    //发送一条
    public Share sendShare(Share share, HttpServletResponse response) throws IOException;
    List<Share> getAll();
    void save(Share share);
    //查询用户的动态信息--szg
    public List<Share> findShareByIdOrderByTime(int userId);
}
