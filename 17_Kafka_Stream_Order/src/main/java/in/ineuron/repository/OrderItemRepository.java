package in.ineuron.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.ineuron.entity.OrderItem;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

}
