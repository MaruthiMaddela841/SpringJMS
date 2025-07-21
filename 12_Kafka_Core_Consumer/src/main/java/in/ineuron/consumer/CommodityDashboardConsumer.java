package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.Commodity;

//@Service
public class CommodityDashboardConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(CommodityDashboardConsumer.class);

	@KafkaListener(topics = "t-commodity", groupId = "consumer-group-dashboard")
	public void consume(String message) {

		try {
			Commodity commodity = objectMapper.readValue(message, Commodity.class);
			LOGGER.info("Dashboard Consumer: {}",commodity);
		} catch (Exception e) {
			LOGGER.error("Error Processing Message");
		}

	}

}
