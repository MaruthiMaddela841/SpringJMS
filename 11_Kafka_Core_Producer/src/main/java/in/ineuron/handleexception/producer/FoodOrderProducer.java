package in.ineuron.handleexception.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.FoodOrder;

//@Service
public class FoodOrderProducer {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendFoodOrder(FoodOrder foodOrder) {
		try {
			var json=objectMapper.writeValueAsString(foodOrder);
			kafkaTemplate.send("t-food-order",json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
