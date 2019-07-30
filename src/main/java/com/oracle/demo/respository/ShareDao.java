package com.oracle.demo.respository;

import com.oracle.demo.entity.Share;
import com.oracle.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShareDao extends JpaRepository<Share,String> ,JpaSpecificationExecutor<Share>{
    @Query(value = "select * from Share where userId=?1 order by updatedAt DESC ",nativeQuery = true)
    public List<Share> findShareByIdOrderByTime(int userId);
    @Modifying
    @org.springframework.transaction.annotation.Transactional
    @Query(value = "delete from Share where id=?1",nativeQuery = true)
    public int deleteshare(Integer id);

    //管理员查看全部动态
    public List<Share> findAllBy();

    public List<Share> findAllByUserId(int id);
    public Share findById(int id);

    //批量删除某一用户的动态
    @Transactional
    public void deleteShareByIdIn(List<Integer> id);
    //查询批量删除是否成功
    @Transactional
    public List<Share> findAllByIdIn(List<Integer> id);

    @Query(value = "select content from Share where id=?1",nativeQuery = true)
    public String findcontent(int id);

    @Transactional
    @Modifying
    @Query(value = "select count(*) from Share",nativeQuery = true)
    public int sharecnum();
}
