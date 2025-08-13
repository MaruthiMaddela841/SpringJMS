package in.ineuron.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import in.ineuron.broker.message.OnlinePaymentMessage;

public class OnlinePaymentTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
        var onlinePaymentMessage = (OnlinePaymentMessage) record.value();

        return onlinePaymentMessage != null ? onlinePaymentMessage.getPaymentDateTime().toInstant().toEpochMilli()
                : record.timestamp();
    }

}
