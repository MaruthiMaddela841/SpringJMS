package com.ineuron.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ineuron.dto.Order;
import com.ineuron.dto.OrderEvent;
import com.ineuron.producer.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
	
	private OrderProducer orderProducer;

	public OrderController(OrderProducer orderProducer) {
		this.orderProducer = orderProducer;
	}
	
	@PostMapping("/orders/email")
	public String placeOrderEmail(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderEvent orderEvent=new OrderEvent();
		orderEvent.setStatus("PENDING");
		orderEvent.setMessage("Order is in pending status");
		orderEvent.setOrder(order);
		orderProducer.sendMessageEmail(orderEvent);
		return "Email Order sent to RabbitMQ";
	}
	
	@PostMapping("/orders/stock")
	public String placeOrderStock(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		OrderEvent orderEvent=new OrderEvent();
		orderEvent.setStatus("OPEN");
		orderEvent.setMessage("Order is in open status");
		orderEvent.setOrder(order);
		orderProducer.sendMessageStock(orderEvent);
		return "Stock Order sent to RabbitMQ";
	}

}
