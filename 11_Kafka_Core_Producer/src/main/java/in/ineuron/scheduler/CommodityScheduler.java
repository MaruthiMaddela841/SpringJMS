package in.ineuron.scheduler;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.ineuron.entity.Commodity;
import in.ineuron.producer.CommodityProducer;

//@Service
public class CommodityScheduler {
	
	private static final String COMMODITY_API_URL="http://localhost:8080/api/commodity/v1/all";
	
	private RestTemplate restTemplate= new RestTemplate();
	
	@Autowired
	private CommodityProducer producer;
	
	@Scheduled(fixedRate = 5000)
	public void fetchAndSendCommodities() {
		Commodity[] commodities=restTemplate.getForObject(COMMODITY_API_URL,Commodity[].class);
		if(commodities!=null) {
			Arrays.stream(commodities).forEach(producer::sendMessage);
		}
	}
	

}
