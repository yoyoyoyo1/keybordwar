package com.oracle.demo.respository;

import com.oracle.demo.entity.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareDao extends JpaRepository<Share,String> {
}
