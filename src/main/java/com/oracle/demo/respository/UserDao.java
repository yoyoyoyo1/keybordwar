package com.oracle.demo.respository;

import com.oracle.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface UserDao extends JpaRepository<User,Integer> {
    @Query(value = "select * from User where email=?1",nativeQuery = true)
    public User findByEmail(String email);
    @Query(value = "select * from User where email=?1 and pass=?2",nativeQuery = true)
    public User userLogin(String email,String pass);
}
