package Ecommerce.repository;

import Ecommerce.entities.Order;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

}
