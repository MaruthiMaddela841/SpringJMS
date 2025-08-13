package in.ineuron.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.SubscriptionPurchaseRequest;
import in.ineuron.broker.message.SubscriptionPurchaseMessage;
import in.ineuron.broker.producer.SubscriptionPurchaseProducer;

@Component
public class SubscriptionPurchaseAction {

	@Autowired
	private SubscriptionPurchaseProducer producer;

	public void publishToKafka(SubscriptionPurchaseRequest request) {
		var message = new SubscriptionPurchaseMessage();

		message.setSubscriptionNumber(request.getSubscriptionNumber());
		message.setUsername(request.getUsername());

		producer.publish(message);
	}

}
