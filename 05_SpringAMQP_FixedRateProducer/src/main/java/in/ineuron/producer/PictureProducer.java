package in.ineuron.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.dto.Picture;

//@Service
public class PictureProducer {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Picture p) {
		rabbitTemplate.convertAndSend("x.picture",p.getType(),p);
	}
}
