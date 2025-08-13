package in.ineuron.command.action;

import java.time.OffsetDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.OnlinePaymentRequest;
import in.ineuron.broker.message.OnlinePaymentMessage;
import in.ineuron.broker.producer.OnlinePaymentProducer;

@Component
public class OnlinePaymentAction {

	@Autowired
	private OnlinePaymentProducer producer;

	public void publishPaymentToKafka(OnlinePaymentRequest request) {
		var message = new OnlinePaymentMessage();

		message.setOnlineOrderNumber(request.getOnlineOrderNumber());
		message.setPaymentNumber("PAY-" + RandomStringUtils.randomAlphanumeric(6).toUpperCase());
		message.setPaymentDateTime(
				request.getPaymentDateTime() == null ? OffsetDateTime.now() : request.getPaymentDateTime());
		message.setPaymentMethod(request.getPaymentMethod());

		producer.publish(message);
	}

}
