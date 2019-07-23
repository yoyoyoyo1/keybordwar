package com.oracle.demo.respository;

import com.oracle.demo.entity.ShareInfo;
import com.oracle.demo.entity.SharePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SharePictureDao extends JpaRepository<SharePicture,Integer> {

    @Query(value = "select * from SharePicture",nativeQuery = true)
    public List<SharePicture> findSharePictureById();
    @Modifying
    @Transactional
    @Query(value = "INSERT into  SharePicture(id,img,shareId) value (null,?1,?2)",nativeQuery = true)
    public int  saveP(String img,int shareId);
    @Query(value = "select * from SharePicture ",nativeQuery = true)
    public List<SharePicture> findimg();
}
