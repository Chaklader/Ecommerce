package Ecommerce.repository;

import Ecommerce.entities.ShoppingCart;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}