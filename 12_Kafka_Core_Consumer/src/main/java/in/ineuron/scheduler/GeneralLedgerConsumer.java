package in.ineuron.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import in.ineuron.consumer.CarLocationConsumer;

@Service
public class GeneralLedgerConsumer {
	
	private static final Logger LOG=LoggerFactory.getLogger(GeneralLedgerConsumer.class);
	
	@KafkaListener(topics="t-general-ledger", id="consumer-ledger-one")
	public void consumeLedgerOne(String message) {
		LOG.info("consumeLedgerOne() consumed message: {}",message);
	}
	
	@KafkaListener(topics="t-general-ledger")
	public void consumeLedgerTwo(String message) {
		LOG.info("consumeLedgerTwo() consumed message: {}",message);
	}

}
