package in.ineuron.broker.stream.commodity;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import in.ineuron.broker.message.OrderMessage;
import in.ineuron.broker.message.OrderPatternMessage;
import in.ineuron.broker.message.OrderRewardMessage;
import in.ineuron.util.CommodityStreamUtil;

//@Component
public class CommodityFiveStream {

    private static final Logger LOG = LoggerFactory.getLogger(CommodityFiveStream.class);

    private void reportFraud(OrderMessage orderMessage) {
        LOG.info("Reporting fraud {}", orderMessage);
    }

    @Autowired
    void kstreamCommodityTrading(final StreamsBuilder builder) {

        final JsonSerde<OrderMessage> orderSerde = new JsonSerde<>(OrderMessage.class);
        final JsonSerde<OrderPatternMessage> orderPatternSerde = new JsonSerde<>(OrderPatternMessage.class);
        final JsonSerde<OrderRewardMessage> orderRewardSerde = new JsonSerde<>(OrderRewardMessage.class);

        // primitive serdes
        final var stringSerde = Serdes.String();
        final var longSerde = Serdes.Long();

        // Source stream: K=String, V=OrderMessage
        final KStream<String, OrderMessage> source = builder.stream(
                "t-commodity-order",
                Consumed.with(stringSerde, orderSerde)
        );

        // Mask credit card: K stays String, V stays OrderMessage
        final KStream<String, OrderMessage> maskedCreditCardStream =
                source.mapValues((OrderMessage v) -> CommodityStreamUtil.maskCreditCardNumber(v));

        // ----- Pattern branch -----
        final KStream<String, OrderPatternMessage> patternStream =
                maskedCreditCardStream.mapValues(
                        (OrderMessage v) -> CommodityStreamUtil.convertToOrderPatternMessage(v)
                );

        patternStream
                .split()
                .branch(
                        CommodityStreamUtil.<String, OrderPatternMessage>isPlastic(),
                        Branched.withConsumer(ks -> ks.to(
                                "t-commodity-pattern-five-plastic",
                                Produced.with(stringSerde, orderPatternSerde)
                        ))
                )
                .defaultBranch(
                        Branched.withConsumer(ks -> ks.to(
                                "t-commodity-pattern-five-notplastic",
                                Produced.with(stringSerde, orderPatternSerde)
                        ))
                );

        // ----- Reward branch -----
        maskedCreditCardStream
                .filter((String k, OrderMessage v) -> CommodityStreamUtil.isLargeQuantity().test(k, v))
                .filterNot((String k, OrderMessage v) -> CommodityStreamUtil.isCheap().test(k, v))
                .map((String k, OrderMessage v) ->
                        CommodityStreamUtil.mapToOrderRewardChangeKey().apply(k, v)
                )
                .to("t-commodity-reward-five", Produced.with(stringSerde, orderRewardSerde));

        // ----- Storage branch (re-key) -----
        maskedCreditCardStream
                .selectKey((String k, OrderMessage v) ->
                        CommodityStreamUtil.generateStorageKey().apply(k, v)
                )
                .to("t-commodity-storage-five", Produced.with(stringSerde, orderSerde));

        // ----- Fraud branch (map to <String, Long>) -----
        maskedCreditCardStream
                .filter((String k, OrderMessage v) ->
                        v.getOrderLocation() != null &&
                        v.getOrderLocation().toUpperCase().startsWith("C")
                )
                .peek((String k, OrderMessage v) -> reportFraud(v))
                .map((String k, OrderMessage v) -> KeyValue.<String, Long>pair(
                        v.getOrderLocation().toUpperCase().charAt(0) + "***",
                        (long) (v.getPrice() * v.getQuantity())
                ))
                .to("t-commodity-fraud-five", Produced.with(stringSerde, longSerde));
    }
}

//package in.ineuron.broker.stream.commodity;
//
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.StreamsBuilder;
//import org.apache.kafka.streams.kstream.Branched;
//import org.apache.kafka.streams.kstream.Consumed;
//import org.apache.kafka.streams.kstream.Produced;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.support.serializer.JsonSerde;
//
//import in.ineuron.broker.message.OrderMessage;
//import in.ineuron.broker.message.OrderPatternMessage;
//import in.ineuron.broker.message.OrderRewardMessage;
//import in.ineuron.util.CommodityStreamUtil;
//
//// @Component
//public class CommodityFiveStream {
//
//        private static final Logger LOG = LoggerFactory.getLogger(CommodityFiveStream.class);
//
//        private void reportFraud(OrderMessage orderMessage) {
//                LOG.info("Reporting fraud {}", orderMessage);
//        }
//
//        @Autowired
//        void kstreamCommodityTrading(StreamsBuilder builder) {
//                var orderSerde = new JsonSerde<>(OrderMessage.class);
//                var orderPatternSerde = new JsonSerde<>(OrderPatternMessage.class);
//                var orderRewardSerde = new JsonSerde<>(OrderRewardMessage.class);
//                var stringSerde = Serdes.String();
//
//                var maskedCreditCardStream = builder
//                                .stream("t-commodity-order", Consumed.with(Serdes.String(), orderSerde))
//                                .mapValues(CommodityStreamUtil::maskCreditCardNumber);
//
//                maskedCreditCardStream.mapValues(CommodityStreamUtil::convertToOrderPatternMessage)
//                                .split()
//                                .branch(CommodityStreamUtil.isPlastic(),
//                                                Branched.<String, OrderPatternMessage>withConsumer(
//                                                                ks -> ks.to("t-commodity-pattern-five-plastic",
//                                                                                Produced.with(stringSerde,
//                                                                                                orderPatternSerde))))
//                                .defaultBranch(
//                                                Branched.<String, OrderPatternMessage>withConsumer(
//                                                                ks -> ks.to("t-commodity-pattern-five-notplastic",
//                                                                                Produced.with(stringSerde,
//                                                                                                orderPatternSerde))));
//
//                maskedCreditCardStream.filter(CommodityStreamUtil.isLargeQuantity())
//                                .filterNot(CommodityStreamUtil.isCheap())
//                                .map(CommodityStreamUtil.mapToOrderRewardChangeKey())
//                                .to("t-commodity-reward-five", Produced.with(Serdes.String(), orderRewardSerde));
//
//                maskedCreditCardStream
//                                .selectKey(CommodityStreamUtil.generateStorageKey())
//                                .to("t-commodity-storage-five", Produced.with(Serdes.String(), orderSerde));
//
//                maskedCreditCardStream.filter(
//                                (k, v) -> v.getOrderLocation().toUpperCase().startsWith("C")).peek(
//                                                (k, v) -> reportFraud(v))
//                                .map(
//                                                (k, v) -> KeyValue.pair(
//                                                                v.getOrderLocation().toUpperCase().charAt(0) + "***",
//                                                                (long) (v.getPrice() * v.getQuantity())))
//                                .to("t-commodity-fraud-five", Produced.with(stringSerde, Serdes.Long()));
//        }
//
//}
