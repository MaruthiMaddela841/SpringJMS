package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;

import in.ineuron.entity.PaymentRequest;

//@Service
public class PaymentRequest2Consumer {
	
	@Autowired
	@Qualifier("cachePaymentRequest")
	private Cache<String,Boolean> cachePaymentRequest;
	
	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentRequest2Consumer.class);
	
	@KafkaListener(topics="t-payment-request",containerFactory = "paymentRequestContainerFactory")
	public void consumePaymentRequest(String json) {
		try {
			var paymentRequest =objectMapper.readValue(json,PaymentRequest.class);
			var cacheKey=paymentRequest.calcuateHash();
			
			LOGGER.info("Processing Payment Request:{}",paymentRequest);
			cachePaymentRequest.put(cacheKey,true);
		}catch(Exception e) {
			LOGGER.error("Error processing payment request",e);
		}
	}

}
