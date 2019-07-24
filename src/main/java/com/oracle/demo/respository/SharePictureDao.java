package com.oracle.demo.respository;

import com.oracle.demo.entity.ShareInfo;
import com.oracle.demo.entity.SharePicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharePictureDao extends JpaRepository<SharePicture,Integer> {

    @Query(value = "select * from SharePicture",nativeQuery = true)
    public List<SharePicture> findSharePictureById();

    List<SharePicture> findAllBy();

    public List<SharePicture> findSharePictureByshareId(int shareid);
}
