package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.SubscriptionPurchaseRequest;
import in.ineuron.command.action.SubscriptionPurchaseAction;

@Service
public class SubscriptionPurchaseService {

	@Autowired
	private SubscriptionPurchaseAction action;

	public void createPurchase(SubscriptionPurchaseRequest request) {
		action.publishToKafka(request);
	}

}
