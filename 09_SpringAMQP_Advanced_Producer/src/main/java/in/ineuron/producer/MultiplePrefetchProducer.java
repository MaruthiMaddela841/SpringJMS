package in.ineuron.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.entity.DummyMessage;

@Service
public class MultiplePrefetchProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void simulateTransaction() {
		for (int i = 0; i < 20_000; i++) {
			var message = new DummyMessage("Transaction " + i, i);

			rabbitTemplate.convertAndSend("x.transaction", "", message);
		}
	}

	public void simulateScheduler() {
		for (int i = 0; i < 200; i++) {
			var message = new DummyMessage("Scheduler " + i, i);

			rabbitTemplate.convertAndSend("x.scheduler", "", message);
		}
	}
}
