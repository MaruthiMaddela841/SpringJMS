package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import in.ineuron.dto.Picture;

//@Service
public class PictureImageConsumer {
	
private static final Logger LOGGER= LoggerFactory.getLogger(PictureImageConsumer.class);
	
	@RabbitListener(queues="q.picture.image")
	public void consume(Picture p) {
		LOGGER.info(String.format("Consumed Picture Image Message -> %s",p.toString()));
	}

}
