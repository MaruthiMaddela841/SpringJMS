package in.ineuron.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import in.ineuron.dto.Employee;

//@Service
public class RetryAccountingConsumer {

	private static final String DEAD_EXCHANGE_NAME = "x.guideline2.dead";

	private static final Logger LOG = LoggerFactory.getLogger(RetryAccountingConsumer.class);
	private DlxProcessingErrorHandler dlxProcessingErrorHandler;

	@Autowired
	private ObjectMapper objectMapper;

	public RetryAccountingConsumer() {
		this.dlxProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
	}

	@RabbitListener(queues = "q.guideline2.accounting.work")
	public void listen(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
			throws IOException {
		try {
			var employee = objectMapper.readValue(message.getBody(), Employee.class);

			if (employee.getName() == null || employee.getName().length() == 0) {
				throw new IllegalArgumentException("Name is empty");
			} else {
				LOG.info("On accounting : {}", employee);
				channel.basicAck(tag, false);
			}
		} catch (Exception e) {
			LOG.warn("Error processing message : {} : {}", new String(message.getBody()), e.getMessage());
			dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel, tag);
		}

	}
}
