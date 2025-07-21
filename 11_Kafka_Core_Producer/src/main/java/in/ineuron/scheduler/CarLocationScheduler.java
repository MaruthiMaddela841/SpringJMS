package in.ineuron.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import in.ineuron.entity.CarLocation;
import in.ineuron.producer.CarLocationProducer;

//@Service
public class CarLocationScheduler {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CarLocationScheduler.class);
	
	private CarLocation carLocationOne;
	private CarLocation carLocationTwo;
	private CarLocation carLocationThree;
	
	@Autowired
	private CarLocationProducer carLocationProducer;

	public CarLocationScheduler() {
		super();
		var now=System.currentTimeMillis();
		this.carLocationOne = new CarLocation("car-one",now,0);
		this.carLocationTwo = new CarLocation("car-two",now,110);
		this.carLocationThree = new CarLocation("car-three",now,95);
	}
	
	@Scheduled(fixedRate = 1000)
	public void generateDummyData() {
		var now=System.currentTimeMillis();
		carLocationOne.setTimestamp(now);
		carLocationTwo.setTimestamp(now);
		carLocationThree.setTimestamp(now);
		
		carLocationOne.setDistance(carLocationOne.getDistance()+1);;
		carLocationTwo.setDistance(carLocationTwo.getDistance()-1);
		carLocationThree.setDistance(carLocationThree.getDistance()+1);
		
		sendCarLocation(carLocationOne);
		sendCarLocation(carLocationTwo);
		sendCarLocation(carLocationThree);
	}

	private void sendCarLocation(CarLocation carLocation) {
		carLocationProducer.sendCarLocation(carLocation);
		LOGGER.info("Sent Car Location: {}",carLocation);
	}

}
