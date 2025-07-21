package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.Employee;

//@Service
public class EmployeeJsonConsumer {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private static final Logger LOGGER= LoggerFactory.getLogger(KafkaKeyConsumer.class);
	
	@KafkaListener(topics = "t-employee-2")
	public void consume(String message){
		
		try {
			var employee = objectMapper.readValue(message,Employee.class);
			LOGGER.info("Employee:{}",employee);
		} catch (JsonMappingException e) {
			LOGGER.info("Error Parsing Employee");
		} catch (JsonProcessingException e) {
			LOGGER.info("Error Parsing Employee");
		}
		
	}

}
