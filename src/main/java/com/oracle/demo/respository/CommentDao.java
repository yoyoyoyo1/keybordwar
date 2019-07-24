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
    @Query(value = "SELECT Comment.id, User.nickname, User.image, Comment.content,Comment.createdAt,1 AS cocNeedId FROM Comment left join User on Comment.userId=User.id WHERE Comment.shareId=?1 ORDER BY createdAt",nativeQuery = true)
    public List<CommentInfo> findCommentByShareId(int shareId);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Comment(content,shareId,userId) VALUES (?1,?2,?3)",nativeQuery = true)
    public int addComment(String content,int shareId,int userId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Share set comments=(select count(*) from Comment WHERE shareId=?1) WHERE id=?2",nativeQuery = true)
    public int updateCommentNum(int shareId,int id);
    @Query(value = "SELECT CommentOfComment.id,CommentOfComment.commentId AS cocNeedId, User.nickname, User.image, CommentOfComment.content,CommentOfComment.createdAt FROM CommentOfComment left join User on CommentOfComment.userId=User.id WHERE CommentOfComment.shareId=?1 ORDER BY createdAt",nativeQuery = true)
    public List<CommentInfo> findCommentOfCommentByShareId(int shareId);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CommentOfComment(commentId,content,shareId,userId) VALUES (?1,?2,?3,?4)",nativeQuery = true)
    public int addCommentOfComment(int commentId,String content,int shareId,int userId);
    @Query(value = "SELECT shareId FROM Comment WHERE id=?1",nativeQuery = true)
    public int findShareIdById(int id);

}
