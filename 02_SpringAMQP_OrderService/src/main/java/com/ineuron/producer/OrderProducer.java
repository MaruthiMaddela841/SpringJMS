package com.ineuron.producer;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ineuron.dto.OrderEvent;

@Service
public class OrderProducer {
	
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(OrderProducer.class);

	@Value("${rabbitmq.order.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.queue.email.order.routing.key}")
	private String emailRoutingKey;
	
	@Value("${rabbitmq.queue.stock.order.routing.key}")
	private String stockRoutingkey;
	
	private RabbitTemplate rabbitTemplate;

	public OrderProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	public void sendMessageEmail(OrderEvent event) {
		rabbitTemplate.convertAndSend(exchange,emailRoutingKey,event);
		LOGGER.info(String.format("Email Message sent -> %s", event.toString()));
	}
	
	public void sendMessageStock(OrderEvent event) {
		rabbitTemplate.convertAndSend(exchange,stockRoutingkey,event);
		LOGGER.info(String.format("Stock Message sent -> %s", event.toString()));
	}
	
	
}
