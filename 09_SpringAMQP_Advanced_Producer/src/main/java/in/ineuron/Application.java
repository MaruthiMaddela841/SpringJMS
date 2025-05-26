package in.ineuron;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import in.ineuron.entity.DummyMessage;
import in.ineuron.producer.DummyProducer;

@SpringBootApplication
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private DummyProducer producer;

	@Override
	public void run(String... args) throws Exception {
		for(int i=0;i<10_000;i++) {
			var dummyMsg=new DummyMessage("Content:"+i, 1);
			producer.sendDummy(dummyMsg);
			TimeUnit.SECONDS.sleep(1);
		}
		
	}

}
