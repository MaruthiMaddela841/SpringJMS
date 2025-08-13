package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.CustomerPurchaseMobileRequest;
import in.ineuron.api.request.CustomerPurchaseWebRequest;
import in.ineuron.command.action.CustomerPurchaseAction;

@Service
public class CustomerPurchaseService {

	@Autowired
	private CustomerPurchaseAction action;

	public String createPurchaseMobile(CustomerPurchaseMobileRequest request) {
		return action.publishMobileToKafka(request);
	}

	public String createPurchaseWeb(CustomerPurchaseWebRequest request) {
		return action.publishWebToKafka(request);
	}
}
