package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Share;
import com.oracle.demo.respository.ShareDao;
import com.oracle.demo.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    ShareDao shareDao;
    public Share sendShare(Share share, HttpServletResponse response) throws IOException {
        /*PrintWriter out=response.getWriter();
        out.print("<html><head><meta charset='UTF-8'></head>");
        out.print("<script>alert('发布成功');</script>");
        out.flush();
        out.close();*/
        return shareDao.save(share);
    }
    @Override
    public List<Share> getAll() {
        return shareDao.findAll();
    }
    @Override
    public void save(Share share){
        shareDao.save(share);
    }
    public List<Share> findShareByIdOrderByTime(int userId)
    {
        return shareDao.findShareByIdOrderByTime(userId);
    }
}
