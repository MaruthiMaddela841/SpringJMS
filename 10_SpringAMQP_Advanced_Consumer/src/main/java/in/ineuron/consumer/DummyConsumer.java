package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import in.ineuron.entity.DummyMessage;


@Service
public class DummyConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(DummyConsumer.class);

	@RabbitListener(queues = "q.dummy")
	public void listenDummy(DummyMessage message) {
		LOG.info("{}", message);
	}

}

//public class ListenerSample {

    // Adjust the header name if required, on @Header parameter
//    @RabbitListener(queues = "q.finance.invoice")
//    public void listenInvoiceCreated(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long tag,
//            @Header("type") String type) throws IOException {
//        if (StringUtils.equalsIgnoreCase(type, "invoice.paid")) {
//            log.info("Delegate to invoice paid handler");
//        } else if (StringUtils.equalsIgnoreCase(type, "invoice.created")) {
//            log.info("Delegate to invoice created handler");
//        } else {
//            log.info("Delegate to default handler");
//        }
//    }
//
//}
