package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.WebLayoutVoteRequest;
import in.ineuron.command.action.WebLayoutVoteAction;

@Service
public class WebLayoutVoteService {

	@Autowired
	private WebLayoutVoteAction action;

	public void createLayoutVote(WebLayoutVoteRequest request) {
		action.publishToKafka(request);
	}

}
