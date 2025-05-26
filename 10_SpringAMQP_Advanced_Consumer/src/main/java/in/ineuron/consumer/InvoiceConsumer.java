package in.ineuron.consumer;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;

import in.ineuron.entity.InvoiceCancelledMessage;
import in.ineuron.entity.InvoiceCreatedMessage;
import in.ineuron.entity.InvoicePaidMessage;
import in.ineuron.entity.PaymentCancelStatus;


//@Service
@RabbitListener(queues = "q.invoice")
public class InvoiceConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(InvoiceConsumer.class);

	@RabbitHandler
	public void handleInvoiceCreated(InvoiceCreatedMessage message) {
		LOG.info("Invoice created : {}", message);
	}

	@RabbitHandler
	public void handleInvoicePaid(InvoicePaidMessage message) {
		LOG.info("Invoice paid : {}", message);
	}

	@RabbitHandler(isDefault = true)
	public void handleDefault(Object message) {
		LOG.info("Default handler : {}", message);
	}

	@RabbitHandler
	@SendTo("x.invoice.cancel/")
	public PaymentCancelStatus handleInvoiceCancelled(InvoiceCancelledMessage message) {
		var randomStatus = ThreadLocalRandom.current().nextBoolean();

		return new PaymentCancelStatus(randomStatus, LocalDate.now(), message.getInvoiceNumber());
	}

}
