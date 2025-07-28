package in.ineuron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import in.ineuron.entity.Image;
import in.ineuron.handleexception.producer.Image2ProcessingProducer;
import in.ineuron.service.ImageService;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner{
	
//	@Autowired
//	private HelloKafkaProducer helloKafkaProducer;
	
//	@Autowired
//	private FixedRateProducer fixedRateProducer;
	
//	@Autowired
//	private KafkaKeyProducer kafkaKeyProducer;
	
//	@Autowired
//	private EmployeeJsonProducer employeeJsonProducer;
	
//	@Autowired
//	private Employee2JsonProducer employee2JsonProducer;
	
//	@Autowired
//	CounterProducer cp=new CounterProducer();
	
//	@Autowired
//	private PurchaseRequestProducer producer;
	
//	@Autowired
//	private PaymentRequestProducer paymentRequestProducer;
	
//	@Autowired
//	private FoodOrderProducer foodOrderProducer;
//	
//	@Autowired
//	private SimpleNumberProducer simpleNumberProducer;
	
//	@Autowired
//	private ImageService imageService;
//	
//	@Autowired
//	private ImageProcessingProducer imageProcessingProducer;
	
//	@Autowired
//	private InvoiceService invoiceService;
//	
//	@Autowired
//	private InvoiceProducer invoiceProducer;
	
	@Autowired
	private Image2ProcessingProducer image2ProcessingProducer;
	
	@Autowired
	private ImageService image2Service;
	
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		helloKafkaProducer.sendHello("Maruthi"+ThreadLocalRandom.current().nextInt(1000));
//		fixedRateProducer.sendMessage();
		
//		for(int i=1;i<=10_000;i++) {
//			String key="Key - "+i;
//			String message="Message "+i;
//			kafkaKeyProducer.sendMessage(key, message);
//			TimeUnit.SECONDS.sleep(1);
//		}
		
//		for(int i=0;i<5;i++) {
//			Employee emp=new Employee(UUID.randomUUID().toString(),"Employee"+i,LocalDate.now().minusYears(20+i));
//			employee2JsonProducer.sendMessage(emp);
//		}
		
//		cp.sendMessage(100);
		
//		PurchaseRequest p1=new PurchaseRequest(UUID.randomUUID(),"REQ-001",100,"USD");
//		PurchaseRequest p2=new PurchaseRequest(UUID.randomUUID(),"REQ-002",200,"EUR");
//		PurchaseRequest p3=new PurchaseRequest(UUID.randomUUID(),"REQ-003",300,"GBP");
//		
//		producer.sendPurchaseRequest(p1);
//		producer.sendPurchaseRequest(p2);
//		producer.sendPurchaseRequest(p3);
//		
//		producer.sendPurchaseRequest(p1);
		
//		PaymentRequest p1=new PaymentRequest(100,"USD","1234567890","Payment 1",LocalDate.now());
//		PaymentRequest p2=new PaymentRequest(200,"USD","1234567890","Payment 2",LocalDate.now());
//		PaymentRequest p3=new PaymentRequest(300,"USD","0987654321","Payment 3",LocalDate.now());
//		PaymentRequest p4=new PaymentRequest(400,"USD","0987654321","Payment 4",LocalDate.now());
//		PaymentRequest p5=new PaymentRequest(500,"USD","1122334455","Payment 5",LocalDate.now());
//		PaymentRequest p6=new PaymentRequest(600,"USD","1122334455","Payment 6",LocalDate.now());
//		
//		paymentRequestProducer.sendPaymentRequest(p1);
//		paymentRequestProducer.sendPaymentRequest(p2);
//		paymentRequestProducer.sendPaymentRequest(p3);
//		paymentRequestProducer.sendPaymentRequest(p4);
//		paymentRequestProducer.sendPaymentRequest(p5);
//		paymentRequestProducer.sendPaymentRequest(p6);
//		
//		paymentRequestProducer.sendPaymentRequest(p1);
//		paymentRequestProducer.sendPaymentRequest(p2);
		 	
//		var chicken= new FoodOrder(3,"Chicken");
//		var fish= new FoodOrder(10,"Fish");
//		var pizza= new FoodOrder(5,"Pizza");
//		
//		foodOrderProducer.sendFoodOrder(chicken);
//		foodOrderProducer.sendFoodOrder(fish);
//		foodOrderProducer.sendFoodOrder(pizza);
//		
//		for(int i=100;i<103;i++) {
//			simpleNumberProducer.sendSimpleNumber(new SimpleNumber(i));
//		}
		
		
//		Image img1= imageService.generateImage("JPG");
//		Image img2= imageService.generateImage("SVG");
//		Image img3= imageService.generateImage("PNG");
//		Image img4= imageService.generateImage("GIF");
//		Image img5= imageService.generateImage("BMP");
//		Image img6= imageService.generateImage("TIFF");
//		
//		imageProcessingProducer.sendImageToPartition(img1, 0);
//		imageProcessingProducer.sendImageToPartition(img2, 0);
//		imageProcessingProducer.sendImageToPartition(img3, 0);
//		
//		imageProcessingProducer.sendImageToPartition(img4, 1);
//		imageProcessingProducer.sendImageToPartition(img5, 1);
//		imageProcessingProducer.sendImageToPartition(img6, 1);
		
//		for(int i=0;i<10;i++) {
//			var invoice=invoiceService.generateInvoice();
//			if(i>5) {
//				invoice.setAmount(0d);
//			}
//			invoiceProducer.sendInvoice(invoice);
//		}
		
		Image img1= image2Service.generateImage("JPG");
		Image img2= image2Service.generateImage("SVG");
		Image img3= image2Service.generateImage("PNG");
		Image img4= image2Service.generateImage("GIF");
		Image img5= image2Service.generateImage("BMP");
		Image img6= image2Service.generateImage("TIFF");
		
		image2ProcessingProducer.sendImageToPartition(img1, 0);
		image2ProcessingProducer.sendImageToPartition(img2, 0);
		image2ProcessingProducer.sendImageToPartition(img3, 0);
		
		image2ProcessingProducer.sendImageToPartition(img4, 1);
		image2ProcessingProducer.sendImageToPartition(img5, 1);
		image2ProcessingProducer.sendImageToPartition(img6, 1);
	}

}
