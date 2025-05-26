package in.ineuron;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import in.ineuron.dto.Employee;
import in.ineuron.producer.RetryEmployeeJsonProducer;

//@EnableScheduling
@SpringBootApplication
public class Application implements CommandLineRunner{
	
//	@Autowired
//	HelloRabbitProducer helloRabbitProducer;
//	
//	@Autowired
//	EmployeeJsonProducer employeeJsonProducer;
//	
//	@Autowired
//	HumanResourcesProducer humanResourcesProducer;
//	
//	@Autowired
//	PictureProducer pictureProducer;
//	
//	@Autowired
//	PictureProducerTwo pictureProducerTwo;
//	
//	@Autowired
//	MyPictureProducer myPictureProducer;
	
//	@Autowired
//	FurnitureProducer furnitureProducer;
	
//	@Autowired
//	RetryPictureProducer retryPictureProducer;

//	@Autowired
//	RetryEmployeeJsonProducer retryEmployeeJsonProducer;
	
	private final List<String> SOURCES=List.of("mobile","web");
	private final List<String> TYPES=List.of("jpg","png","svg");
	
	private final List<String> COLORS=List.of("red","white","green");
	private final List<String> MATERIALS=List.of("steel","wood","plastic");

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		helloRabbitProducer.publishMessage("My Message::"+ThreadLocalRandom.current().nextInt());
		
//		for(int i=0;i<=5;i++) {
//			humanResourcesProducer.sendMessage(new Employee("EMP-"+i,"Employee-"+i,LocalDate.of(2025, 1, 1+i)));
//		}
		
//		for(int i=0;i<1;i++) {
//			var p=new Picture();
//			p.setName("Picture :"+i);
//			p.setSize(ThreadLocalRandom.current().nextLong(9001,10000));
//			p.setSource(SOURCES.get(i%SOURCES.size()));
//			p.setType(TYPES.get(i%TYPES.size()));
//			myPictureProducer.sendMessage(p);
//		}
		
//		for(int i=0;i<10;i++) {
//			var furniture= new Furniture();
//			furniture.setName("Furniture:"+i);
//			furniture.setColor(COLORS.get(i % COLORS.size()));
//			furniture.setMaterial(MATERIALS.get(i % MATERIALS.size()));
//			furniture.setPrice(i);
//			furnitureProducer.sendMessage(furniture);
//		}
		
//		for(int i=0;i<10;i++) {
//		var p=new Picture();
//		p.setName("Picture :"+i);
//		p.setSize(ThreadLocalRandom.current().nextLong(9001,10000));
//		p.setSource(SOURCES.get(i%SOURCES.size()));
//		p.setType(TYPES.get(i%TYPES.size()));
//		retryPictureProducer.sendMessage(p);
//	}
		
//		for(int i=0;i<10;i++) {
//			retryEmployeeJsonProducer.sendMessage(new Employee("EMP-"+i,"Employee-"+i,LocalDate.of(2025, 1, 1+i)));
//		}
		
		
	}

}
