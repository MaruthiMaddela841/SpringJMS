package in.ineuron.consumer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DLQConsumer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;

	public void handleDeadLetterResend(String message) {
	    System.out.println("Dead letter: " + message);
	    // Retry logic
	    amqpTemplate.convertAndSend("main.exchange", "main.routing.key", message);
	}

    @RabbitListener(queues = "dlx.queue")
    public void handleDeadLetter(String message) {
        System.out.println("⚠️ Dead Letter received: " + message);
        handleDeadLetterResend(message);
        // Add retry logic, alerts, etc. here
    }
}

