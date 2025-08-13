package in.ineuron.broker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import in.ineuron.broker.message.SubscriptionPurchaseMessage;

@Service
public class SubscriptionPurchaseProducer {

	@Autowired
	private KafkaTemplate<String, SubscriptionPurchaseMessage> kafkaTemplate;

	public void publish(SubscriptionPurchaseMessage message) {
		kafkaTemplate.send("t-commodity-subscription-purchase", message.getUsername(), message);
	}

}
