package in.ineuron.consumer;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

//@Service
public class CounterConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(CounterConsumer.class);
	
	@KafkaListener(topics = "t-counter", groupId = "counter-group-fast")
	public void consumeFast(String message) {

		System.out.println("Fast: "+message);

	}
	
	@KafkaListener(topics = "t-counter", groupId = "counter-group-slow")
	public void consumeSlow(String message) throws InterruptedException {
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Slow: "+message);

	}

}
