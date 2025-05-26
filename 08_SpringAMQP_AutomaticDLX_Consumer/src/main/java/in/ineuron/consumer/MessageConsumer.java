package in.ineuron.consumer;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

@Service
public class MessageConsumer {

    @RabbitListener(queues = "main.queue")
    public void receive(String message, Channel channel, Message amqpMessage) throws IOException {
        System.out.println("Received: " + message);

        // Simulate failure: reject and send to DLQ
        channel.basicReject(amqpMessage.getMessageProperties().getDeliveryTag(), false);
    }
}

