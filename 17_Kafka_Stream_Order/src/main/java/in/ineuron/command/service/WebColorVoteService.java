package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.WebColorVoteRequest;
import in.ineuron.command.action.WebColorVoteAction;

@Service
public class WebColorVoteService {

	@Autowired
	private WebColorVoteAction action;

	public void createColorVote(WebColorVoteRequest request) {
		action.publishToKafka(request);
	}

}
