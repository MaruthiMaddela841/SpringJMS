package in.ineuron.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.dto.Picture;

//@Service
public class PictureProducerTwo {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(Picture p) {
		var sb= new StringBuilder();
		
		//1st word is mobile or web (picture source)
		sb.append(p.getSource());
		sb.append(".");
		
		//2nd word is large or small based on picture size
		if(p.getSize()>4000) {
			sb.append("large");
		} else {
			sb.append("small");
		}
		sb.append(".");
		
		//3rd word is picture type
		sb.append(p.getType());
		
		var routingKey=sb.toString();
		//mobile.large.jpg
		rabbitTemplate.convertAndSend("x.picture2",routingKey,p);
	}
}
