package com.ineuron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ineuron.dto.User;
import com.ineuron.producer.RabbitMQJsonProducer;
import com.ineuron.producer.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
	
	@Autowired
	private RabbitMQProducer mqProducer;
	
	@Autowired
	private RabbitMQJsonProducer jsonProducer;
	
	@GetMapping("/publish")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
		mqProducer.sendMessage(message);
		return ResponseEntity.ok("Message sent to RabbitMQ...");
	}
	
	@PostMapping("/json")
	public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
		jsonProducer.sendJsonMessage(user);
		return ResponseEntity.ok("Json Message sent to RabbitMQ...");
	}

}
