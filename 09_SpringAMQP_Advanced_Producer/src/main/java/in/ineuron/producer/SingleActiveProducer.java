package in.ineuron.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.entity.DummyMessage;

@Service
public class SingleActiveProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendDummy() {
		for (int i = 0; i < 10_000; i++) {
			var message = new DummyMessage("Dummy " + i, i);

			rabbitTemplate.convertAndSend("x.single", "", message);
		}
	}

}
