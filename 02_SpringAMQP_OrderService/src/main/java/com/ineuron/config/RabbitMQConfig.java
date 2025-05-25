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
	
	@Value("${rabbitmq.order.exchange.name}")
	private String exchange;
	
	@Value("${rabbitmq.queue.stock.order.name}")
	private String stockQueue;
	
	@Value("${rabbitmq.queue.email.order.name}")
	private String emailQueue;
	
	@Value("${rabbitmq.queue.stock.order.routing.key}")
	private String stockRoutingkey;
	
	@Value("${rabbitmq.queue.email.order.routing.key}")
	private String emailRoutingKey;

	//spring bean for RabbitMQ queue
	@Bean
	public Queue stockQueue() {
		return new Queue(stockQueue);
	}
	
	@Bean
	public Queue emailQueue() {
		return new Queue(emailQueue);
	}
	
	//spring bean for RabbitMQ exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}
	
	//spring bean for RabbitMQ binding
	@Bean
	public Binding stockQueueBinding() {
		return  BindingBuilder.bind(stockQueue())
				.to(exchange())
				.with(stockRoutingkey);
	}
	
	@Bean
	public Binding emailQueueBinding() {
		return  BindingBuilder.bind(emailQueue())
				.to(exchange())
				.with(emailRoutingKey);
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
