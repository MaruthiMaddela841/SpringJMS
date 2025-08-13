package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.SubscriptionUserRequest;
import in.ineuron.command.action.SubscriptionUserAction;

@Service
public class SubscriptionUserService {

	@Autowired
	private SubscriptionUserAction action;

	public void createUser(SubscriptionUserRequest request) {
		action.publishToKafka(request);
	}

}
