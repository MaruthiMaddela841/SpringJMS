package in.ineuron.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import in.ineuron.dto.Picture;

//@Service
public class PictureVectorConsumer {
	
private static final Logger LOGGER= LoggerFactory.getLogger(PictureVectorConsumer.class);
	
	@RabbitListener(queues="q.picture.vector")
	public void consume(Picture p) {
		LOGGER.info(String.format("Consumed Picture Vector Message -> %s",p.toString()));
	}

}
