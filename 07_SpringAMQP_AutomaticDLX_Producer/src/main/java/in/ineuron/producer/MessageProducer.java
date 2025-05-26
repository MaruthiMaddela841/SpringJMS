package in.ineuron.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final AmqpTemplate amqpTemplate;

    public MessageProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void send(String message) {
        amqpTemplate.convertAndSend("main.exchange", "main.routing.key", message);
    }
}

