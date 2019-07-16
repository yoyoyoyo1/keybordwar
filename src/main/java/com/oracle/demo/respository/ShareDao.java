package com.oracle.demo.respository;

import com.oracle.demo.entity.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShareDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Share share) {
        // TODO Auto-generated method stub
        jdbcTemplate.update("insert into Share (content,userId,) values(?)", share.getContent(),share.getUserId());
    }
}
