package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.PremiumUserRequest;
import in.ineuron.command.action.PremiumUserAction;

@Service
public class PremiumUserService {

	@Autowired
	private PremiumUserAction action;

	public void createUser(PremiumUserRequest request) {
		action.publishToKafka(request);
	}

}
