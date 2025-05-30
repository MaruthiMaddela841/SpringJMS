package in.ineuron.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.entity.DummyMessage;

@Service
public class DummyProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendDummy(DummyMessage dummyMessage) {
		rabbitTemplate.convertAndSend("x.dummy", "", dummyMessage);
	}

}
