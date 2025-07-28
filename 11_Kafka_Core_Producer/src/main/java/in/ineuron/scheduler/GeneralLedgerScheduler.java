package in.ineuron.scheduler;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import in.ineuron.schedulerproducer.GeneralLedgerProducer;

@Service
public class GeneralLedgerScheduler {
	
	private static final AtomicInteger COUNTER= new AtomicInteger();
	
	@Autowired
	private GeneralLedgerProducer generalLedgerProducer;
	
	@Scheduled(fixedRate = 1000)
	public void schedule() {
		generalLedgerProducer.sendGeneralLedger("Message: "+COUNTER.incrementAndGet());
	}

}
