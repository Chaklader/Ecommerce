package Ecommerce.service.def;

import Ecommerce.entities.ShippingAddress;
import Ecommerce.entities.UserShipping;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface ShippingAddressService {

	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}