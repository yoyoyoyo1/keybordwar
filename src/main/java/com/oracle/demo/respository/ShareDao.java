package com.oracle.demo.respository;

import com.oracle.demo.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareDao extends JpaRepository<Share,String> {
    @Query(value = "select * from Share where userId=?1 order by updatedAt DESC ",nativeQuery = true)
    public List<Share> findShareByIdOrderByTime(int userId);
    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "delete from Share where id=?1",nativeQuery = true)
    public int deleteshare(int id);
}
