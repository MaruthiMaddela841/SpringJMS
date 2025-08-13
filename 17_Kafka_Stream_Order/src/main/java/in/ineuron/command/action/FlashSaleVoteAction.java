package in.ineuron.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.FlashSaleVoteRequest;
import in.ineuron.broker.message.FlashSaleVoteMessage;
import in.ineuron.broker.producer.FlashSaleVoteProducer;

@Component
public class FlashSaleVoteAction {

	@Autowired
	private FlashSaleVoteProducer producer;

	public void publishToKafka(FlashSaleVoteRequest request) {
		var message = new FlashSaleVoteMessage();

		message.setCustomerId(request.getCustomerId());
		message.setItemName(request.getItemName());

		producer.publish(message);
	}

}
