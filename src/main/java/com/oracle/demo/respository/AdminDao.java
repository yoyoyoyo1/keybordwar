package com.oracle.demo.respository;

import com.oracle.demo.entity.Admin;
import com.oracle.demo.entity.User;
import com.sun.mail.imap.protocol.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface AdminDao extends JpaRepository<Admin,Integer> {
    //管理员登录
    public Admin findAdminByAccountAndPassword(String account,String password);

    //@Modifying
    //@Query(value = "insert into User(id,email,image,motto,nickname,pass,phome) VALUE (?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    //添加用户
    public User save(User user);

    //补全用户昵称的查询
    @Query(value = "select nickname from User where nickname=?1",nativeQuery = true)
    public List<String> findnkey(String k);

}
