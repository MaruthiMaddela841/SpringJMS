package in.ineuron.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class HelloRabbitProducer {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public void publishMessage(String message) {
		rabbitTemplate.convertAndSend("course.hello",message);
	}

}
