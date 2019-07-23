package com.oracle.demo.respository;

import com.oracle.demo.entity.ShareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareInfoDao extends JpaRepository<ShareInfo,String> {

    @Query(value = "select Share.userId,Share.id,User.nickname,User.image,Share.content,Share.comments,Share.likes,Share.forwards,Share.createdAt from Share left join User on Share.userId = User.id    " +
              "  ORDER BY createdAt DESC",nativeQuery = true)
    public List<ShareInfo> findtime();
}