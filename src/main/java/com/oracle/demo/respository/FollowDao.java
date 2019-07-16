package com.oracle.demo.respository;

import com.oracle.demo.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FollowDao extends JpaRepository<Follow,Integer> {
    @Query(value = "select COUNT(follower) AS following from Follow where follower =?1")
    public int showFollowing(int id);
    @Query(value = "select COUNT(following) AS follower from Follow where following=?1")
    public int showFollower(int id);
}
