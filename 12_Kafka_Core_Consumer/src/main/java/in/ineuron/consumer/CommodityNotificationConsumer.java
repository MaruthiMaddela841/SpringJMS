package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.Commodity;
import in.ineuron.entity.Employee;

//@Service
public class CommodityNotificationConsumer {

	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(CommodityNotificationConsumer.class);

	@KafkaListener(topics = "t-commodity", groupId = "consumer-group-notification")
	public void consume(String message) {

		try {
			Commodity commodity = objectMapper.readValue(message, Commodity.class);
			LOGGER.info("Notification Consumer: {}",commodity);
		} catch (Exception e) {
			LOGGER.error("Error Processing Message");
		}

	}

}
