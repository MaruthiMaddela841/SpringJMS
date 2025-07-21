package in.ineuron.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.PaymentRequest;

//@Service
public class PaymentRequestProducer {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendPaymentRequest(PaymentRequest paymentRequest) {
		try {
			String paymentRequestJson=objectMapper.writeValueAsString(paymentRequest);
			kafkaTemplate.send("t-payment-request",paymentRequestJson);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
