package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.FlashSaleVoteRequest;
import in.ineuron.command.action.FlashSaleVoteAction;

@Service
public class FlashSaleVoteService {

	@Autowired
	private FlashSaleVoteAction action;

	public void createFlashSaleVote(FlashSaleVoteRequest request) {
		action.publishToKafka(request);
	}

}
