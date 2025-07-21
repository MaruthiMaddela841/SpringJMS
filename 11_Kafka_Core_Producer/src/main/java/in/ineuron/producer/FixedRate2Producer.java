package in.ineuron.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class FixedRate2Producer {
	
	private static final Logger LOGGER= LoggerFactory.getLogger(FixedRate2Producer.class);
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	private int i=0;
	
	@Scheduled(fixedRate=1000)
	public void sendMessage() {
		i++;
		LOGGER.info("i is :"+i);
		kafkaTemplate.send("t-fixedrate2","Fixed rate 2 :: "+i);
	}

}
