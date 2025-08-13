package in.ineuron.broker.stream.customer.preference;

import org.apache.kafka.streams.kstream.Aggregator;

import in.ineuron.broker.message.CustomerPreferenceAggregateMessage;
import in.ineuron.broker.message.CustomerPreferenceWishlistMessage;

public class CustomerPreferenceWishlistAggregator
        implements Aggregator<String, CustomerPreferenceWishlistMessage, CustomerPreferenceAggregateMessage> {

    @Override
    public CustomerPreferenceAggregateMessage apply(String key, CustomerPreferenceWishlistMessage value,
            CustomerPreferenceAggregateMessage aggregate) {
        aggregate.putWishlistItem(value.getItemName(), value.getWishlistDatetime());

        return aggregate;
    }

}
