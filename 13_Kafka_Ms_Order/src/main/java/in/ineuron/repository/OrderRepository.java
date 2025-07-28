package in.ineuron.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.ineuron.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
