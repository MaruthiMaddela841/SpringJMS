package com.ineuron.consumer;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.ineuron.dto.User;
import com.ineuron.producer.RabbitMQJsonProducer;
import com.ineuron.producer.RabbitMQProducer;

@Service
public class RabbitMQJsonConsumer {
	
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(RabbitMQJsonConsumer.class);
	
	
	@RabbitListener(queues="${rabbitmq.jsonqueue.name}")
	public void jsonConsume(User user) {
		LOGGER.info(String.format("Received Json Message -> %s", user.toString()));
	}

}
