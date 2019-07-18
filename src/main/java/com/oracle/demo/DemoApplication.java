package com.oracle.demo;

import com.oracle.demo.websocket.dialogWebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		dialogWebSocket.Dialog.put("1",new HashMap<>());
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("nmsl");
	}


}
