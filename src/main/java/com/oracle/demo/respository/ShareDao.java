package com.oracle.demo.respository;

import com.oracle.demo.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareDao extends JpaRepository<Share,String> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Share share) {
        // TODO Auto-generated method stub
        jdbcTemplate.update("insert into Share (content,userId,) values(?)", share.getContent(),share.getUserId());
    }
}
