package com.oracle.demo.respository;

import com.oracle.demo.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DialogDao extends JpaRepository<Dialog,Integer>,JpaSpecificationExecutor<Dialog> {
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

    //批量删除圆桌
    @Modifying
    @Transactional
    public void deleteDialogByIdIn(List<Integer> id);
    //查询批量删除是否成功
    @Transactional
    public List<Dialog> findAllByIdIn(List<Integer> id);
    //单个删除圆桌
    @Modifying
    @Transactional
    public int deleteDialogById(int id);

    //编辑圆桌
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update Dialog set title=?1,content=?2,image=?3 where id=?4",nativeQuery = true)
    public int updateDialog(String title,String content,String image,int id);
    //查找圆桌编辑
    public Dialog findById(int id);
    //启用圆桌
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update Dialog set active=?1 where id=?2",nativeQuery = true)
    public int updateDialogon(int active,int id);
    //关闭圆桌
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update Dialog  set active=?1 where  id=?2",nativeQuery = true)
    public int updateDialogoff(int active,int id);


}
