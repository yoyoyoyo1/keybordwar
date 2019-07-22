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
    @Query(value = "select * from Message where toId=?1 AND formId=?1 and watch=1 order by createAt",nativeQuery = true)
    List<Message> getchatMessageById(Integer formId,Integer toId);
    @Query(value = "select distinct  formId from  Message where toId=?1 and unix_timestamp(createAt) < ?2 and watch in (1,0) union SELECT distinct toId FROM Message where formId=?1 and unix_timestamp(createAt) < ?2 and watch in (1,0)",nativeQuery = true)
    public List<Integer> latelyTalk(Integer userId,Long time);
    @Query(value = " SELECT following FROM Follow WHERE follower =?1 and following in (select follower FROM Follow where following = ?1)",nativeQuery = true)
    public List<Integer> eachOtherFollow(Integer userId);
}
