package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.CarLocation;

//@Service
public class CarLocationConsumer {
	
	private static final Logger LOG=LoggerFactory.getLogger(CarLocationConsumer.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@KafkaListener(topics = "t-location",groupId = "group-location-all")
	public void listenAll(String message) throws JsonMappingException, JsonProcessingException {
		var carLocation=objectMapper.readValue(message,CarLocation.class);
		LOG.info("Listen All :: {}"+carLocation);
	}
	
	@KafkaListener(topics = "t-location",groupId = "group-location-far",containerFactory = "locationFarContainerFactory")
	public void listenFar(String message) throws JsonMappingException, JsonProcessingException {
		var carLocation=objectMapper.readValue(message,CarLocation.class);
		LOG.info("Listen Far :: {}"+carLocation);
	}

}
