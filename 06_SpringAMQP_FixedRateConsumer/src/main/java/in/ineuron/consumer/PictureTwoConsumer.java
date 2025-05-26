package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import in.ineuron.dto.Picture;

//@Service
public class PictureTwoConsumer {
	
private static final Logger LOGGER= LoggerFactory.getLogger(PictureTwoConsumer.class);
	
	@RabbitListener(queues={"q.picture.image","q.picture.vector","q.picture.filter","q.picture.log"})
	public void consume(Picture p) {
		LOGGER.info(String.format("Consuming: %s with routing key : %s",p.toString(),p.getSource()+"->"+p.getType()));
	}

}
