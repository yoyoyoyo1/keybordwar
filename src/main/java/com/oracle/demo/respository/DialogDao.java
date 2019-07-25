package com.oracle.demo.respository;

import com.oracle.demo.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DialogDao extends JpaRepository<Dialog,Integer> {
    @Query(value = "SELECT * FROM Dialog WHERE active = ?1 ORDER BY id limit ?2,10",nativeQuery = true)
    public List<Dialog> getDialogs(int active,int page);
    public List<Dialog> getDialogsByActive(int active);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Dialog SET active = 0 WHERE active = 1 and unix_timestamp(createdAt) < ?1",nativeQuery = true)
    public int updateAll(Long time);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Dialog SET active = 0 WHERE id=?1",nativeQuery = true)
    public int updateActive(Integer i);
}
