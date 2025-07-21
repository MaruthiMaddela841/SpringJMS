package in.ineuron.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.Employee;

//@Service
public class Employee2JsonProducer {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendMessage(Employee emp) {
		try {
			var json=objectMapper.writeValueAsString(emp);
			kafkaTemplate.send("t-employee-2",json);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
