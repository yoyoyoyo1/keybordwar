package com.oracle.demo.respository;

import com.oracle.demo.entity.Likes;
import com.oracle.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LikeDao extends JpaRepository<Likes,Integer> {
    @Query(value = "select * from Likes where shareId=?1 AND userId=?2",nativeQuery = true)
    public Likes findLike(int shareId,int userId);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Likes (shareId, userId) VALUES (?1, ?2)",nativeQuery = true)
    public int addALike(int shareId,int userId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Likes WHERE shareId=?1 AND userId=?2",nativeQuery = true)
    public int deleteLike(int shareId,int userId);
    @Query(value = "select count(*) from Likes WHERE shareId=?1",nativeQuery = true)
    public int findLikeNum(int shareId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Share set likes=?1 WHERE id=?2",nativeQuery = true)
    public int updateLikes(int likes,int id);
}
