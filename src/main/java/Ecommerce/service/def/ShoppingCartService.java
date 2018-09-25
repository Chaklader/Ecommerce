package Ecommerce.service.def;

import Ecommerce.entities.ShoppingCart;

/**
 * Created by Chaklader on Oct, 2017
 */
public interface ShoppingCartService {

    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    void clearShoppingCart(ShoppingCart shoppingCart);
}