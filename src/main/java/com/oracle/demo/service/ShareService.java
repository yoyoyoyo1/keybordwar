package com.oracle.demo.service;

import com.oracle.demo.entity.Share;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ShareService {
    //发送一条
    public Share sendShare(Share share, HttpServletResponse response) throws IOException;
    List<Share> getAll();
}
