package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.PromotionRequest;
import in.ineuron.command.action.PromotionAction;

@Service
public class PromotionService {

    @Autowired
    private PromotionAction action;

    public void createPromotion(PromotionRequest request) {
        var message = action.convertToPromotionMessage(request);
        action.sendToKafka(message);
    }

}
