package in.ineuron.handleexception.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.Image;

@Service
public class Image2ProcessingProducer {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void sendImageToPartition(Image image,int partition) {
		
		try {
			var imageJson=objectMapper.writeValueAsString(image);
			kafkaTemplate.send("t-image-2",partition,image.getType(),imageJson);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
