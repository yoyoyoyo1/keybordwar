package com.oracle.demo.service;

import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.ShareInfo;
import com.oracle.demo.entity.SharePicture;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ShareService {
    //发送一条
    public Share sendShare(Share share, HttpServletResponse response) throws IOException;
    List<ShareInfo> getAll();
    List<SharePicture>getAllPicture();
    void savePicture(SharePicture sharePicture);
    void save(Share share);
    //查询用户的动态信息--szg
    public List<Share> findShareByIdOrderByTime(int userId);
}
