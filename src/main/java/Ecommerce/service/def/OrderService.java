package Ecommerce.service.def;

import Ecommerce.entities.*;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart,
                      ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			User user);
	
	Order findOne(Long id);
}
