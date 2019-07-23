package com.oracle.demo.respository;

import com.oracle.demo.entity.Comment;
import com.oracle.demo.entity.CommentInfo;
import com.oracle.demo.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Repository
public interface CommentDao extends JpaRepository<CommentInfo,String> {
    @Query(value = "SELECT Comment.id, User.nickname, Comment.content,Comment.createdAt FROM Comment left join User on Comment.userId=User.id WHERE Comment.shareId=?1 ORDER BY createdAt",nativeQuery = true)
    public List<CommentInfo> findCommentByShareId(int shareId);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Comment(content,shareId,userId) VALUES (?1,?2,?3)",nativeQuery = true)
    public int addComment(String content,int shareId,int userId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Share set comments=(select count(*) from Comment WHERE shareId=?1) WHERE id=?2",nativeQuery = true)
    public int updateCommentNum(int shareId,int id);

}
