package com.oracle.demo.service;

import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.ShareInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ShareService {
    //发送一条
    public Share sendShare(Share share, HttpServletResponse response) throws IOException;
    List<ShareInfo> getAll();
    void save(Share share);
}
