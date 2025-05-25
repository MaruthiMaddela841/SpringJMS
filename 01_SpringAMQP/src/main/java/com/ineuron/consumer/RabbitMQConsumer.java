package com.ineuron.consumer;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.ineuron.dto.User;
import com.ineuron.producer.RabbitMQJsonProducer;
import com.ineuron.producer.RabbitMQProducer;

@Service
public class RabbitMQConsumer {
	
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(RabbitMQConsumer.class);
	
	@RabbitListener(queues="${rabbitmq.queue.name}")
	public void consume(String message) {
		LOGGER.info(String.format("Received Message -> %s", message));
	}

}
