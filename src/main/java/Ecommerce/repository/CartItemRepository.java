package Ecommerce.repository;


import Ecommerce.entities.CartItem;
import Ecommerce.entities.Order;
import Ecommerce.entities.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Chaklader on Oct, 2017
 */
public interface CartItemRepository extends CrudRepository<CartItem, Long> {

    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    List<CartItem> findByOrder(Order order);
}