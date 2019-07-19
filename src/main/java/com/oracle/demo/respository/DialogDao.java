package com.oracle.demo.respository;

import com.oracle.demo.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DialogDao extends JpaRepository<Dialog,Integer> {
    public List<Dialog> getDialogsByActive(int i);
}
