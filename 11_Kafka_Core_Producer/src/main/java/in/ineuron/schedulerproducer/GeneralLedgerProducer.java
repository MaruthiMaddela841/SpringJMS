package in.ineuron.schedulerproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ineuron.entity.CarLocation;

@Service
public class GeneralLedgerProducer {
	
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	public void sendGeneralLedger(String message) {
		
			kafkaTemplate.send("t-general-ledger",message);
	}
	
	

}
