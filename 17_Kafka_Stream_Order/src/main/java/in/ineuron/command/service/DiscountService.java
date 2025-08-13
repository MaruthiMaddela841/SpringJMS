package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.DiscountRequest;
import in.ineuron.command.action.DiscountAction;

@Service
public class DiscountService {

	@Autowired
	private DiscountAction action;

	public void createDiscount(DiscountRequest request) {
		var discountMessage = action.convertToMessage(request);
		action.sendToKafka(discountMessage);
	}

}
