package in.ineuron.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GeneralLedgerScheduler {
	
	@Autowired
	private KafkaListenerEndpointRegistry endpointRegistry;
	
	private static final Logger LOG=LoggerFactory.getLogger(GeneralLedgerScheduler.class);
	
	@Scheduled(cron="0 30 0 * * *")
	public void pause() {
		LOG.info("Pause Listener");
		endpointRegistry.getListenerContainer("consumer-ledger-one").pause();
	}
	
	@Scheduled(cron="0 32 0 * * *")
	public void resume() {
		LOG.info("Resume Listener");
		endpointRegistry.getListenerContainer("consumer-ledger-one").resume();
	}

}
