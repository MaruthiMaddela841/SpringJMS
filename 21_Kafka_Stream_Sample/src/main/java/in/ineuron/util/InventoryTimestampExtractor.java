package in.ineuron.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import in.ineuron.broker.message.InventoryMessage;

public class InventoryTimestampExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> record, long partitionTime) {
        var inventoryMessage = (InventoryMessage) record.value();

        return inventoryMessage != null ? inventoryMessage.getTransactionTime().toInstant().toEpochMilli()
                : record.timestamp();
    }

}
