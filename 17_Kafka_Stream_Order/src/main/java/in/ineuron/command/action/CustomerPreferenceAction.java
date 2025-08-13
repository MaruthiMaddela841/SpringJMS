package in.ineuron.command.action;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ineuron.api.request.CustomerPreferenceShoppingCartRequest;
import in.ineuron.api.request.CustomerPreferenceWishlistRequest;
import in.ineuron.broker.message.CustomerPreferenceShoppingCartMessage;
import in.ineuron.broker.message.CustomerPreferenceWishlistMessage;
import in.ineuron.broker.producer.CustomerPreferenceProducer;

@Component
public class CustomerPreferenceAction {

	@Autowired
	private CustomerPreferenceProducer producer;

	public void publishShoppingCart(CustomerPreferenceShoppingCartRequest request) {
		var message = new CustomerPreferenceShoppingCartMessage(request.getCustomerId(), request.getItemName(),
				request.getCartAmount(), OffsetDateTime.now());

		producer.publishShoppingCart(message);
	}

	public void publishWishlist(CustomerPreferenceWishlistRequest request) {
		var message = new CustomerPreferenceWishlistMessage(request.getCustomerId(), request.getItemName(),
				OffsetDateTime.now());

		producer.publishWishlist(message);
	}

}
