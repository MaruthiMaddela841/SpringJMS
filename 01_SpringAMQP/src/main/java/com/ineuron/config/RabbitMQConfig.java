package com.ineuron.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	
	@Value("${rabbitmq.queue.name}")
	private String queue;
	
	@Value("${rabbitmq.jsonqueue.name}")
	private String jsonQueue;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.routingkey.name}")
	private String routingKey;
	
	@Value("${rabbitmq.jsonroutingkey.name}")
	private String jsonRoutingKey;

	//spring bean for RabbitMQ queue
	@Bean
	public Queue queue() {
		return new Queue(queue);
	}
	
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue);
	}
	
	//spring bean for RabbitMQ exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	//spring bean for RabbitMQ binding
	@Bean
	public Binding binding() {
		return  BindingBuilder.bind(queue())
				.to(exchange())
				.with(routingKey);
	}
	
	@Bean
	public Binding jsonBinding() {
		return  BindingBuilder.bind(jsonQueue())
				.to(exchange())
				.with(jsonRoutingKey);
	}
	
	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
	
	//ConnectionFactory
	//RabbitTemplate
	//RabbitAdmin
}
