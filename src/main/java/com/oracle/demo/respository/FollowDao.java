package com.oracle.demo.respository;

import com.oracle.demo.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface FollowDao extends JpaRepository<Follow,Integer> {
    @Query(value = "select COUNT(follower) AS following from Follow where follower =?1")
    public int showFollowing(int id);
    @Query(value = "select COUNT(following) AS follower from Follow where following=?1")
    public int showFollower(int id);
    @Query(value = "select follower from Follow where following =?1")
    public List<Integer> followMeList(int id);
    @Query(value = "select following from Follow where follower=?1")
    public List<Integer> followingList(int id);
    @Query(value = "select * from Follow where follower =?1 and following =?2",nativeQuery = true)
    public Follow isFollow(int follower,int following);
    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "delete from Follow where follower=?1 and following=?2",nativeQuery = true)
    public int undoFollow(int follower,int following);
    @Query(value = "select * from Follow where follower=?1 and following=?2",nativeQuery = true)
    public Follow isFollwta(int follower,int following);
}
