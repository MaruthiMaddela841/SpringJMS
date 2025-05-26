package in.ineuron.consumer;

import java.io.IOException;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

import in.ineuron.dto.Picture;

@Service
public class MyPictureImageConsumer {
	
private static final Logger LOGGER= LoggerFactory.getLogger(MyPictureImageConsumer.class);
	
	@RabbitListener(queues="q.mypicture.image")
	public void consume(Picture p,Channel channel,@Header (AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
		if(p.getSize()>9000) {
//			throw new AmqpRejectAndDontRequeueException("Picture size too large:"+p); //Automatic Rejection using Spring
			channel.basicReject(tag, false);// Manual Rejection
		}
		LOGGER.info(String.format("Processing Image -> %s",p.toString()));
		
		channel.basicAck(tag, false);//Manual Acknowledgement
	}

}
