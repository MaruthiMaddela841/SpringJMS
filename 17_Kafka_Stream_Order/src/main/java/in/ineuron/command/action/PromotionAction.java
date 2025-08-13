package in.ineuron.command.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.PromotionRequest;
import in.ineuron.broker.message.PromotionMessage;
import in.ineuron.broker.producer.PromotionProducer;

@Component
public class PromotionAction {

    @Autowired
    private PromotionProducer producer;

    public PromotionMessage convertToPromotionMessage(PromotionRequest request) {
        return new PromotionMessage(request.getPromotionCode());
    }

    public void sendToKafka(PromotionMessage message) {
        producer.sendPromotion(message);
    }
}