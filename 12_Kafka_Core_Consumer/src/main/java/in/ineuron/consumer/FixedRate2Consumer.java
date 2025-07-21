package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class FixedRate2Consumer {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(FixedRate2Consumer.class);
	
	@KafkaListener(topics = "t-fixedrate2")
	public void consume(String message) {
		LOGGER.info("Consuming Message::"+message);
	}

}
