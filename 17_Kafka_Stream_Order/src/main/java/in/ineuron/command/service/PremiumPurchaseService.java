package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.PremiumPurchaseRequest;
import in.ineuron.command.action.PremiumPurchaseAction;

@Service
public class PremiumPurchaseService {

	@Autowired
	private PremiumPurchaseAction action;

	public void createPurchase(PremiumPurchaseRequest request) {
		action.publishToKafka(request);
	}

}
