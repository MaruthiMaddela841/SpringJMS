package in.ineuron.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.dto.Employee;

//@Service
public class HumanResourcesProducer {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Employee emp) {
		rabbitTemplate.convertAndSend("x.hr","",emp);
	}

}
