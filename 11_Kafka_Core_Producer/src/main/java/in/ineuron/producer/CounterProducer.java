package in.ineuron.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.Commodity;

//@Service
public class CounterProducer {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendMessage(int num) {
		
		for(int i=0;i<num;i++) {
			String message="Data "+i;
			kafkaTemplate.send("t-counter",message);
		}
		
	}

}
