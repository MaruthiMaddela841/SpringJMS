package in.ineuron.broker.stream.orderpayment;

import java.time.Duration;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;

import in.ineuron.broker.message.OnlineOrderMessage;
import in.ineuron.broker.message.OnlineOrderPaymentMessage;
import in.ineuron.broker.message.OnlinePaymentMessage;
import in.ineuron.util.OnlineOrderTimestampExtractor;
import in.ineuron.util.OnlinePaymentTimestampExtractor;

// @Component
public class OrderPaymentOneStream {

    private OnlineOrderPaymentMessage joinOrderPayment(OnlineOrderMessage order, OnlinePaymentMessage payment) {
        var result = new OnlineOrderPaymentMessage();

        result.setOnlineOrderNumber(order.getOnlineOrderNumber());
        result.setOrderDateTime(order.getOrderDateTime());
        result.setTotalAmount(order.getTotalAmount());
        result.setUsername(order.getUsername());

        result.setPaymentDateTime(payment.getPaymentDateTime());
        result.setPaymentMethod(payment.getPaymentMethod());
        result.setPaymentNumber(payment.getPaymentNumber());

        return result;
    }

    @Autowired
    void kstreamOrderPayment(StreamsBuilder builder) {
        var stringSerde = Serdes.String();
        var orderSerde = new JsonSerde<>(OnlineOrderMessage.class);
        var paymentSerde = new JsonSerde<>(OnlinePaymentMessage.class);
        var orderPaymentSerde = new JsonSerde<>(OnlineOrderPaymentMessage.class);

        var orderStream = builder.stream("t-commodity-online-order", Consumed.with(
                stringSerde, orderSerde, new OnlineOrderTimestampExtractor(), null));

        var paymentStream = builder.stream("t-commodity-online-payment", Consumed.with(
                stringSerde, paymentSerde, new OnlinePaymentTimestampExtractor(), null));

        orderStream.join(paymentStream, this::joinOrderPayment,
                JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofHours(1l)),
                StreamJoined.with(stringSerde, orderSerde, paymentSerde))
                .to("t-commodity-join-order-payment-one", Produced.with(stringSerde, orderPaymentSerde));

    }

}
