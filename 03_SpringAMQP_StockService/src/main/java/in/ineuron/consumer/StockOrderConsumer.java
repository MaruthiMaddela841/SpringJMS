package in.ineuron.consumer;

import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import in.ineuron.dto.OrderEvent;


@Service
public class StockOrderConsumer {
	
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(StockOrderConsumer.class);
	
	@RabbitListener(queues="${rabbitmq.queue.stock.order.name}")
	public void consume(OrderEvent event) {
		
		LOGGER.info(String.format("Stock Order Event Received -> %s",event.toString()));
	}

}
