package com.oracle.demo.respository;

import com.oracle.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {
    @Query(value = "select * from User where email=?1",nativeQuery = true)
    public User findByEmail(String email);
    @Query(value = "select * from User where email=?1 and pass=?2",nativeQuery = true)
    public User userLogin(String email,String pass);
    @Query(value = "select id from User where email = ?1",nativeQuery = true)
    public int findIdByEmail(String email);
    public List<User> findAllBy();
    @Query(value = "select * from User where id in ?1",nativeQuery = true)
    public List<User> followMe(List<Integer> followme);
    @Query(value = "select * from User where id = ?1",nativeQuery = true)
    public User findById(int id);
    @Modifying
    @Transactional
    @Query(value = "update User set image = ?1 where id = ?2",nativeQuery = true)
    public int changeImg(String name,int id);
}