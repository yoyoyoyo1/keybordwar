package com.oracle.demo.respository;

import com.oracle.demo.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zxd
 * @create 2017-03-31 16:36
 **/
@Repository
public interface InfoDao extends JpaRepository<Info,String> {
    @Query(value = "SELECT * FROM Info WHERE userId=?1 ORDER BY createAt desc",nativeQuery = true)
    public List<Info> getInfos(int userId);
}