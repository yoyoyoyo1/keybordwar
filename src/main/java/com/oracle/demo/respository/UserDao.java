package com.oracle.demo.respository;

import com.oracle.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
    @Override
            //插入用户
    <S extends User> S save(S s);
}
