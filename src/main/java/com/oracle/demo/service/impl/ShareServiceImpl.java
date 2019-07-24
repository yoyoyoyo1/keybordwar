package com.oracle.demo.service.impl;

import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.ShareInfo;
import com.oracle.demo.entity.SharePicture;
import com.oracle.demo.respository.ShareDao;
import com.oracle.demo.respository.ShareInfoDao;
import com.oracle.demo.respository.SharePictureDao;
import com.oracle.demo.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ShareServiceImpl implements ShareService {
    @Autowired
    ShareDao shareDao;
    @Autowired
    ShareInfoDao shareInfoDao;
    @Autowired
    SharePictureDao sharePictureDao;
    public Share sendShare(Share share, HttpServletResponse response) throws IOException {
        /*PrintWriter out=response.getWriter();
        out.print("<html><head><meta charset='UTF-8'></head>");
        out.print("<script>alert('发布成功');</script>");
        out.flush();
        out.close();*/
        return shareDao.save(share);
    }
    @Override
    public List<ShareInfo> getAll() {
        return shareInfoDao.findtime();
    }
    @Override
    public int getShareNum(){
        return shareInfoDao.countShareNum();
    }
    @Override
    public List<ShareInfo> getByPage(int page) {
        page=5*page;
        return shareInfoDao.findShareByPage(page);
    }
    public List<ShareInfo> getOnesBypage(int userId,int page)
    {
        page=5*page;
        return shareInfoDao.findShareByOnesPage(userId,page);
    }
    public int getOneShareNum(int userId)
    {
        return shareInfoDao.countOneShareNum(userId);
    }


    @Override
    public List<SharePicture>getAllPicture(){return sharePictureDao.findSharePictureById();}

    @Override
    public void savePicture(String img,int shareId){
       int x= sharePictureDao.saveP(img,shareId );
        System.out.println("dksjafh:"+x);
    }

    @Override
    public void save(Share share){
        shareDao.save(share);
    }
    public List<Share> findShareByIdOrderByTime(int userId)
    {
        return shareDao.findShareByIdOrderByTime(userId);
    }
    public int deleteshare(int id)
    {
        return shareDao.deleteshare(id);
    }
    public List<ShareInfo> findOne(int id)
    {
        return shareInfoDao.findOne(id);
    }

}
