package in.ineuron.handleexception.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.FoodOrder;
import in.ineuron.entity.SimpleNumber;

//@Service
public class SimpleNumberConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleNumberConsumer.class);
	
	@KafkaListener(topics="t-simple-number")
	public void consume(String json) throws JsonMappingException, JsonProcessingException{
		SimpleNumber value = objectMapper.readValue(json,SimpleNumber.class);
		int number=value.getNumber();
		if(number%2!=0) {
			throw new IllegalArgumentException("Number is odd: "+number);
		}
		LOGGER.info("Number is even: "+number);
	}

}
