package com.oracle.demo.respository;

import com.oracle.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {
    @Query(value = "select * from User where email=?1",nativeQuery = true)
    public User findByEmail(String email);
    @Query(value = "select * from User where email=?1 and pass=?2",nativeQuery = true)
    public User userLogin(String email,String pass);
    @Query(value = "select id from User where email = ?1",nativeQuery = true)
    public int findIdByEmail(String email);
    //查询全部用户
    public List<User> findAllBy();
    //模糊查询昵称
    public List<User> findAllByNicknameLike(String nkey);
    //模糊查询邮箱
    public List<User> findAllByEmailLike(String ekey);
    //模糊查询手机
    public List<User> findAllByPhoneLike(String pkey);
    //批量删除
    @Transactional
    public void deleteUserByIdIn(List<Integer> id);
    //查询批量删除是否成功
    @Transactional
    public List<User> findAllByIdIn(List<Integer> id);
    //分页查询用户(未实现)
    //blic Page<User> getUserListByIdContaining(String mohu, Pageable pageable);
}