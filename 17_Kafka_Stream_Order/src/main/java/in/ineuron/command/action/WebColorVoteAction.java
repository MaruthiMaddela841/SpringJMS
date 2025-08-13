package in.ineuron.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.WebColorVoteRequest;
import in.ineuron.broker.message.WebColorVoteMessage;
import in.ineuron.broker.producer.WebColorVoteProducer;

@Component
public class WebColorVoteAction {

	@Autowired
	private WebColorVoteProducer producer;

	public void publishToKafka(WebColorVoteRequest request) {
		var message = new WebColorVoteMessage();

		message.setUsername(request.getUsername());
		message.setColor(request.getColor());
		message.setVoteDateTime(request.getVoteDateTime());

		producer.publish(message);
	}

}
