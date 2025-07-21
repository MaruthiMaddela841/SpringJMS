package in.ineuron.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.PurchaseRequest;

//@Service
public class PurchaseRequestProducer {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendPurchaseRequest(PurchaseRequest purchaseRequest) {
		try {
			String purchaseRequestJson=objectMapper.writeValueAsString(purchaseRequest);
			kafkaTemplate.send("t-purchase-request",purchaseRequest.getRequestNumber(),purchaseRequestJson);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
