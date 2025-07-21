package in.ineuron.consumer;

import java.util.concurrent.TimeUnit;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class RebalanceConsumer {
	
	@KafkaListener(topics = {"t-rebalance-alpha","t-rebalance-beta"},groupId = "group-rebalance",concurrency = "3")
	public void consumer(String message) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(5);
	}

}
