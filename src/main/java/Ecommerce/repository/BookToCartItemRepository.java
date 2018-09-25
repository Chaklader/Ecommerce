package Ecommerce.repository;


import Ecommerce.entities.BookToCartItem;
import Ecommerce.entities.CartItem;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {

	void deleteByCartItem(CartItem cartItem);
}
