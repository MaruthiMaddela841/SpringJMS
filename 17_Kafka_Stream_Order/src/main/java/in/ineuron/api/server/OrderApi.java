package in.ineuron.api.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.api.request.OrderRequest;
import in.ineuron.api.response.OrderResponse;
import in.ineuron.command.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

    @Autowired
    private OrderService service;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        // 1. save order using the service
        var orderNumber = service.saveOrder(request);

        // 2. construct OrderResponse
        var response = new OrderResponse(orderNumber);

        // 3. return the response
        return ResponseEntity.ok().body(response);
    }

}
