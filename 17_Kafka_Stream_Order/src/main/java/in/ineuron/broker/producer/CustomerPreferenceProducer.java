package in.ineuron.broker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import in.ineuron.broker.message.CustomerPreferenceShoppingCartMessage;
import in.ineuron.broker.message.CustomerPreferenceWishlistMessage;

@Service
public class CustomerPreferenceProducer {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void publishShoppingCart(CustomerPreferenceShoppingCartMessage message) {
		kafkaTemplate.send("t-commodity-customer-preference-shopping-cart", message.getCustomerId(), message);
	}

	public void publishWishlist(CustomerPreferenceWishlistMessage message) {
		kafkaTemplate.send("t-commodity-customer-preference-wishlist", message.getCustomerId(), message);
	}

}
