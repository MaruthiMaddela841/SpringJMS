package in.ineuron.broker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import in.ineuron.broker.message.PremiumPurchaseMessage;

@Service
public class PremiumPurchaseProducer {

	@Autowired
	private KafkaTemplate<String, PremiumPurchaseMessage> kafkaTemplate;

	public void publish(PremiumPurchaseMessage message) {
		kafkaTemplate.send("t-commodity-premium-purchase", message.getPurchaseNumber(), message);
	}

}
