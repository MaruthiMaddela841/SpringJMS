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

//@Service
public class FoodOrderConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(FoodOrderConsumer.class);
	
	private static final int MAX_AMOUNT_ORDER=7;
	
	@KafkaListener(topics="t-food-order",errorHandler = "myFoodOrderErrorHandler")
	public void consume(String json) throws JsonMappingException, JsonProcessingException {
		FoodOrder foodOrder = objectMapper.readValue(json, FoodOrder.class);
		if(foodOrder.getAmount()>MAX_AMOUNT_ORDER) {
			LOGGER.error("Amount {} exceeds the maximum allowed amount of {}",foodOrder.getAmount(),MAX_AMOUNT_ORDER);
			throw new IllegalArgumentException("Order Amount exceeds the maximum limit");
		}
		LOGGER.info("Consumed Food Order: {}",foodOrder);
	}

}
