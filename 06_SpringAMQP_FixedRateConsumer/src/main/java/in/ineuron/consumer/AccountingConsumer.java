package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import in.ineuron.dto.Employee;

//@Service
public class AccountingConsumer {
	
private static final Logger LOGGER= LoggerFactory.getLogger(AccountingConsumer.class);
	
	@RabbitListener(queues="q.hr.accounting")
	public void consume(Employee emp) {
		LOGGER.info(String.format("Consumed AccountingEmployee Message -> %s",emp.toString()));
	}

}
