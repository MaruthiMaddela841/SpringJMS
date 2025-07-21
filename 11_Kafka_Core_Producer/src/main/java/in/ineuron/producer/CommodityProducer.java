package in.ineuron.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.Commodity;

//@Service
public class CommodityProducer {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendMessage(Commodity commodity) {
		try {
			var json=objectMapper.writeValueAsString(commodity);
			kafkaTemplate.send("t-commodity",commodity.getName(),json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
