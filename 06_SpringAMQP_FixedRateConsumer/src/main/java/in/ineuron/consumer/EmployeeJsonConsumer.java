package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import in.ineuron.dto.Employee;

//@Service
public class EmployeeJsonConsumer {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeJsonConsumer.class);
	
	@RabbitListener(queues="course.employee")
	public void consume(Employee emp) {
		LOGGER.info(String.format("Consumed Employee Message -> %s",emp.toString()));
	}

}
