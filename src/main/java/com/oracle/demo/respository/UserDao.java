package com.oracle.demo.respository;

import com.oracle.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User,Integer>, PagingAndSortingRepository<User,Integer>,JpaSpecificationExecutor<User> {
    @Query(value = "select * from User where email=?1",nativeQuery = true)
    public User findByEmail(String email);
    @Query(value = "select * from User where email=?1 and pass=?2",nativeQuery = true)
    public User userLogin(String email,String pass);
    @Query(value = "select id from User where email = ?1",nativeQuery = true)
    public int findIdByEmail(String email);
    //查询全部用户
    public List<User> findAllBy();
    //分页查询
    public Page<User> findAll(Pageable pageable);
    //模糊查询昵称
    public List<User> findAllByNicknameLike(String nkey);
    //模糊查询邮箱
    public List<User> findAllByEmailLike(String ekey);
    //模糊查询手机
    public List<User> findAllByPhoneLike(String pkey);
    //批量删除
    @Transactional
    public void deleteUserByIdIn(List<Integer> id);
    //修改用户的信息
    @Transactional
    @Modifying
    @Query(value = "update User set email = ?1,pass = ?2,nickname = ?3,phone = ?4,image = ?5,motto = ?6 where id=?7",nativeQuery = true)
    public int  edituser(String email,String pass, String nickname, String phone, String image, String motto,int id);
    //
    @Transactional
    @Modifying
    public int deleteUserById(Integer id);
    //查询批量删除是否成功
    @Transactional
    public List<User> findAllByIdIn(List<Integer> id);
    //修改用户头像为默认头像
    @Transactional
    @Modifying
    @Query(value = "update User set image = ?1 where id=?2",nativeQuery = true)
    public int edituserhimg(String image,int id);
    //管理员修改用户的密码
    @Transactional
    @Modifying
    @Query(value = "update User set pass = ?1 where  id=?2",nativeQuery = true)
    public int edituserpass(String newpass,int id);
    //分页查询用户(未实现)
    //blic Page<User> getUserListByIdContaining(String mohu, Pageable pageable);
    @Query(value = "select * from User where id in ?1",nativeQuery = true)
    public List<User> followMe(List<Integer> followme);
    @Query(value = "select * from User where id in (?1) ",nativeQuery = true)
    public List<User> findByIds(List<Integer> ids);
    @Query(value = "select * from User where id = ?1 ",nativeQuery = true)
    public User findById(int id);
    @Modifying
    @Transactional
    @Query(value = "update User set image = ?1 where id = ?2",nativeQuery = true)
    public int changeImg(String name,int id);
    @Modifying
    @Transactional
    @Query(value = "update User set nickname = ?1,motto =?2 where id = ?3",nativeQuery = true)
    public int updateinfo(String nickname,String motto,int id);
    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "update User set pass = ?1 where id = ?2",nativeQuery = true)
    public int updatepwd(String pass,int id);
    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "update User set pass=?1 where email=?2",nativeQuery = true)
    public int changepwd(String pass,String email);
    @Query(value = "select * from User where email = ?1 and phone=?2",nativeQuery = true)
    public User findByEmailAndPhone(String email,String phone);
}