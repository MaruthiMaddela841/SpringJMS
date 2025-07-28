package in.ineuron.handleexception.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.FoodOrder;
import in.ineuron.entity.Image;

@Service
public class Image2ProcessingConsumer {

	@Autowired
	private ObjectMapper objectMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(Image2ProcessingConsumer.class);

	@RetryableTopic(
			autoCreateTopics = "true",
			attempts = "4",
			topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
			backoff = @Backoff(
					delay=3000,
					maxDelay = 10000,
					multiplier = 1.5,
					random=true
					),
			dltTopicSuffix = "-dead"
			)
	@KafkaListener(topics="t-image-2",concurrency = "2")
	public void consume(String message,@Header(KafkaHeaders.RECEIVED_PARTITION) int partition) throws JsonMappingException, JsonProcessingException{
		Image image=objectMapper.readValue(message,Image.class);
		if("svg".equalsIgnoreCase(image.getType())) {
			throw new IllegalArgumentException("SVG type are not allowed");
		}
		LOGGER.info("Consumed message: {} from partition: {}",message,partition);
	}

}
