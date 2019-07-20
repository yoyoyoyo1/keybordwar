package com.oracle.demo;

import com.oracle.demo.entity.Dialog;
import com.oracle.demo.service.DialogService;
import com.oracle.demo.websocket.dialogWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;


@SpringBootApplication
public class DemoApplication {


	public static void main(String[] args) {


		SpringApplication.run(DemoApplication.class, args);
		System.out.println("nmsl");
	}
}
