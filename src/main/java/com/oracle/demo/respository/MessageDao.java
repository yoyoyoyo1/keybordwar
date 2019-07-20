package com.oracle.demo.respository;


import com.oracle.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageDao extends JpaRepository<Message,Integer> {
    @Query(value = "select * from  Message where toId=?1 and watch=2 ORDER BY createAt", nativeQuery = true)
    public List<Message> getDialogMessageByIdAndTime(int dialogId, Long time);

    @Query(value = "select * from Message where toId=?1 and watch=1 order by createAt",nativeQuery = true)
    List<Message> getchatMessageByIdAndTime(int toId, long time);

}