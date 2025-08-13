package in.ineuron.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.WebLayoutVoteRequest;
import in.ineuron.broker.message.WebLayoutVoteMessage;
import in.ineuron.broker.producer.WebLayoutVoteProducer;

@Component
public class WebLayoutVoteAction {

	@Autowired
	private WebLayoutVoteProducer producer;

	public void publishToKafka(WebLayoutVoteRequest request) {
		var message = new WebLayoutVoteMessage();

		message.setUsername(request.getUsername());
		message.setLayout(request.getLayout());
		message.setVoteDateTime(request.getVoteDateTime());

		producer.publish(message);
	}

}
