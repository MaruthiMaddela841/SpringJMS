package in.ineuron.broker.serde;

import in.ineuron.broker.message.PromotionMessage;

public class PromotionSerde extends CustomJsonSerde<PromotionMessage> {

    public PromotionSerde() {
        super(new CustomJsonSerializer<>(), new CustomJsonDeserializer<>(PromotionMessage.class));
    }

}
