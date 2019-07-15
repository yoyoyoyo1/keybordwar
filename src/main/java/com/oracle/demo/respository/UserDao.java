package com.oracle.demo.respository;

import com.oracle.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface UserDao extends JpaRepository<User,Integer> {
    @Modifying
    @Query(value = "insert into User (email,pass,phone,nickname) values(?1,?2,?3,?4)",nativeQuery = true)
    int addOne(String email,String pass,String phone,String nickname);
}
