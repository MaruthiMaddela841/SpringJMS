package in.ineuron.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.api.request.OrderRequest;
import in.ineuron.broker.message.OrderMessage;
import in.ineuron.command.action.OrderAction;
import in.ineuron.entity.Order;

@Service
public class OrderService {

    @Autowired
    private OrderAction action;

    public String saveOrder(OrderRequest request) {
        Order orderEntity = action.convertToOrder(request);

        action.saveToDatabase(orderEntity);

        orderEntity.getOrderItems().forEach(item -> {
            OrderMessage orderMessage = action.convertToOrderMessage(item);

            action.sendToKafka(orderMessage);
        });

        return orderEntity.getOrderNumber();
    }

}
