package com.ineuron.producer;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
	
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(RabbitMQProducer.class);

	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routingkey.name}")
	private String routingKey;
	
	private RabbitTemplate rabbitTemplate;

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	public void sendMessage(String message) {
		LOGGER.info(String.format("Message sent -> %s", message));
		rabbitTemplate.convertAndSend(exchange,routingKey,message);
	}
	
	
}
