package in.ineuron.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public TopicExchange mainExchange() {
        return new TopicExchange("main.exchange");
    }

    @Bean
    public Queue mainQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dlx.exchange"); // Link to DLX
        args.put("x-dead-letter-routing-key", "dlx.routing.key");
        args.put("x-message-ttl", 5000); // Auto DLX after 5s if unconsumed
        return new Queue("main.queue", true, false, false, args);
    }

    @Bean
    public Binding mainBinding() {
        return BindingBuilder.bind(mainQueue())
            .to(mainExchange())
            .with("main.routing.key");
    }

    // ---- DLQ setup ----
    @Bean
    public TopicExchange dlxExchange() {
        return new TopicExchange("dlx.exchange");
    }

    @Bean
    public Queue dlq() {
        return new Queue("dlx.queue");
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(dlq())
            .to(dlxExchange())
            .with("dlx.routing.key");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}

