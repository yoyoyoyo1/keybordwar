package com.oracle.demo.respository;

import com.oracle.demo.entity.Message;
import com.oracle.demo.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends JpaRepository<Message,Integer> {
}
