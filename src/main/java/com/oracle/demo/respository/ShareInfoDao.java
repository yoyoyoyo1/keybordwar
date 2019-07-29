package com.oracle.demo.respository;

import com.oracle.demo.entity.ShareInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShareInfoDao extends JpaRepository<ShareInfo,String> {

    @Query(value = "select Share.userId,Share.id,User.nickname,User.image,Share.content,Share.comments,Share.likes,Share.forwards,Share.createdAt,10 AS likeInfo from Share left join User on Share.userId = User.id    " +
              "  ORDER BY createdAt DESC LIMIT 5",nativeQuery = true)
    public List<ShareInfo> findtime();
    @Query(value = "select Share.userId,Share.id,User.nickname,User.image,Share.content,Share.comments,Share.likes,Share.forwards,Share.createdAt,10 AS likeInfo from Share left join User on Share.userId = User.id    " +
            "where Share.userId = ?1 ORDER BY createdAt DESC LIMIT 5",nativeQuery = true)
    public List<ShareInfo> findOne(int id);
    @Query(value = "select Share.userId,Share.id,User.nickname,User.image,Share.content,Share.comments,Share.likes,Share.forwards,Share.createdAt,10 AS likeInfo from Share left join User on Share.userId = User.id    " +
            "ORDER BY createdAt DESC LIMIT ?1,5",nativeQuery = true)
    public List<ShareInfo> findShareByPage(int page);
    @Query(value = "select Share.userId,Share.id,User.nickname,User.image,Share.content,Share.comments,Share.likes,Share.forwards,Share.createdAt,10 AS likeInfo from Share left join User on Share.userId = User.id    " +
            "where Share.userId = ?1 ORDER BY createdAt DESC LIMIT ?2,5",nativeQuery = true)
    public List<ShareInfo> findShareByOnesPage(int userId,int page);
    @Query(value = "SELECT COUNT(*) FROM Share ",nativeQuery = true)
    public int countShareNum();
    @Query(value = "SELECT COUNT(*) FROM Share where Share.userId = ?1",nativeQuery = true)
    public int countOneShareNum(int userId);

}