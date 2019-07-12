package com.oracle.demo.respository;

import com.oracle.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Member,Integer> {
}
