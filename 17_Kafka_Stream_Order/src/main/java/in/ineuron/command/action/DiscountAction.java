package in.ineuron.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.DiscountRequest;
import in.ineuron.broker.message.DiscountMessage;
import in.ineuron.broker.producer.DiscountProducer;

@Component
public class DiscountAction {

	@Autowired
	private DiscountProducer producer;

	public DiscountMessage convertToMessage(DiscountRequest request) {
		return new DiscountMessage(request.getDiscountCode(), request.getDiscountPercentage());
	}

	public void sendToKafka(DiscountMessage message) {
		producer.publish(message);
	}

}
