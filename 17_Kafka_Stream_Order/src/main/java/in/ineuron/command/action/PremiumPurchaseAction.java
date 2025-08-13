package in.ineuron.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.PremiumPurchaseRequest;
import in.ineuron.broker.message.PremiumPurchaseMessage;
import in.ineuron.broker.producer.PremiumPurchaseProducer;

@Component
public class PremiumPurchaseAction {

	@Autowired
	private PremiumPurchaseProducer producer;

	public void publishToKafka(PremiumPurchaseRequest request) {
		var message = new PremiumPurchaseMessage();

		message.setUsername(request.getUsername());
		message.setItem(request.getItem());
		message.setPurchaseNumber(request.getPurchaseNumber());

		producer.publish(message);
	}

}
