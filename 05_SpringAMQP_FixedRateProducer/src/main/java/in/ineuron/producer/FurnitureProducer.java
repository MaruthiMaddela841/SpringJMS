package in.ineuron.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.dto.Furniture;

//@Service
public class FurnitureProducer {
	
private static final Logger LOGGER=LoggerFactory.getLogger(FurnitureProducer.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendMessage(Furniture f) throws JsonProcessingException {
		var messageProperties = new MessageProperties();
		messageProperties.setHeader("color", f.getColor());
		messageProperties.setHeader("material", f.getMaterial());
		String json = objectMapper.writeValueAsString(f);
		var message=new Message(json.getBytes(),messageProperties);
		rabbitTemplate.convertAndSend("x.promotion","",message);
	}

}
