package com.wright.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wright.web.message.MessageCom;

@RestController
public class TestController {

	@Autowired
	private MessageCom messageCom;
	
	@GetMapping("test-stream")
	public String testStream(String str){
		messageCom.send("sdfsdfsd"+str);
	    return "success"+str;
	}
}
