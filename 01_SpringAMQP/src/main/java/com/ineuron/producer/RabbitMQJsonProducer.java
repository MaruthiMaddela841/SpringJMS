package com.ineuron.producer;

import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ineuron.dto.User;

@Service
public class RabbitMQJsonProducer {
	
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(RabbitMQJsonProducer.class);
	
	@Value("${rabbitmq.jsonqueue.name}")
	private String jsonQueue;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.jsonroutingkey.name}")
	private String jsonRoutingKey;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	
	public void sendJsonMessage(User user) {
		amqpTemplate.convertAndSend(exchange,jsonRoutingKey,user);
		LOGGER.info(String.format("Json Message sent -> %s", user.toString()));
	}

}
