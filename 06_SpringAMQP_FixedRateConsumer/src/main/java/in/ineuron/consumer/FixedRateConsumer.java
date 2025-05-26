package in.ineuron.consumer;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class FixedRateConsumer {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(FixedRateConsumer.class);
	
	@RabbitListener(queues = "course.fixedrate",concurrency = "3-7")
	public void listen(String message) throws InterruptedException {
		LOGGER.info("Thread {} consuming: {}",Thread.currentThread().getName(), message);
		TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextLong(2000));
	}

}
