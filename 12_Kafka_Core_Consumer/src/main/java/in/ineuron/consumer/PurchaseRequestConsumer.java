package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;

import in.ineuron.entity.PurchaseRequest;

//@Service
public class PurchaseRequestConsumer {
	
	@Autowired
	@Qualifier("cachePurchaseRequest")
	private Cache<String,Boolean> cachePurchaseRequest;
	
	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseRequestConsumer.class);
	
	private boolean isExistsInCache(String requestNumber) {
		return cachePurchaseRequest.getIfPresent(requestNumber)!=null;
	}
	
	@KafkaListener(topics="t-purchase-request")
	public void consumePurchaseRequest(String json) {
		try {
			var purchaseRequest =objectMapper.readValue(json,PurchaseRequest.class);
			if(isExistsInCache(purchaseRequest.getRequestNumber())) {
				LOGGER.warn("Purchase Request already exists in cachce::",purchaseRequest.getRequestNumber());
				return;
			}
			
			LOGGER.info("Processing Purchase Request:{}",purchaseRequest.getRequestNumber());
			cachePurchaseRequest.put(purchaseRequest.getRequestNumber(),true);
		}catch(Exception e) {
			LOGGER.error("Error processing purchase request",e);
		}
	}

}
