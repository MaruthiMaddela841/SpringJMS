package in.ineuron.broker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import in.ineuron.broker.message.InventoryMessage;

@Service
public class InventoryProducer {

	@Autowired
	private KafkaTemplate<String, InventoryMessage> kafkaTemplate;

	public void publish(InventoryMessage message) {
		kafkaTemplate.send("t-commodity-inventory", message.getItem(), message);
	}

}
