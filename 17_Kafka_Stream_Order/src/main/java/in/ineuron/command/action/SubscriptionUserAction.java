package in.ineuron.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.SubscriptionUserRequest;
import in.ineuron.broker.message.SubscriptionUserMessage;
import in.ineuron.broker.producer.SubscriptionUserProducer;

@Component
public class SubscriptionUserAction {

	@Autowired
	private SubscriptionUserProducer producer;

	public void publishToKafka(SubscriptionUserRequest request) {
		var message = new SubscriptionUserMessage();

		message.setDuration(request.getDuration());
		message.setUsername(request.getUsername());

		producer.publish(message);
	}

}
