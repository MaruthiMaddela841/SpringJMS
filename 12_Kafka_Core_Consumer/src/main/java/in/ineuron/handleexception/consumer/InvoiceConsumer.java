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
import in.ineuron.entity.Invoice;

//@Service
public class InvoiceConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceConsumer.class);
	
	@KafkaListener(topics="t-invoice",concurrency = "2",containerFactory = "invoiceDltContainerFactory")
	public void consume(String json) throws JsonMappingException, JsonProcessingException {
		var invoice =objectMapper.readValue(json,Invoice.class);
		if(invoice.getAmount()<1) {
			throw new IllegalArgumentException("Invalid Amount:"+invoice.getAmount());
		}
		
		LOGGER.info("Consumed Invoice: {}",invoice);
	}

}
