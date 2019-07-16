package com.oracle.demo.respository;

import com.oracle.demo.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareDao extends JpaRepository<Share,Integer> {
}
