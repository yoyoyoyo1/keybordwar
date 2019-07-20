package com.oracle.demo.respository;


import com.oracle.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageDao extends JpaRepository<Message,Integer> {
    @Query(value = "select * from  Message where toId=?1 and watch=2 ORDER BY createAt",nativeQuery = true)
    public List<Message> getDialogMessageByIdAndTime(int dialogId);
    @Query(value = "select distinct  formId from  Message where toId=?1 and watch=2 ",nativeQuery = true)
    public List<Integer> getFormIdByDialogId(Integer dialogId);
}
