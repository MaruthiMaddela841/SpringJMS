package in.ineuron.command.action;

import java.time.OffsetDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.FeedbackRequest;
import in.ineuron.broker.message.FeedbackMessage;
import in.ineuron.broker.producer.FeedbackProducer;

@Component
public class FeedbackAction {

	@Autowired
	private FeedbackProducer producer;

	public void publishToKafka(FeedbackRequest request) {
		var message = new FeedbackMessage();
		message.setFeedback(request.getFeedback());
		message.setLocation(request.getLocation());
		message.setRating(request.getRating());
		// random date time between last 7 days - now
		message.setFeedbackDateTime(OffsetDateTime.now().minusHours(ThreadLocalRandom.current().nextLong(7 * 7)));

		producer.publish(message);
	}

}
