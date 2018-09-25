package Ecommerce.service.def;

import Ecommerce.entities.BillingAddress;
import Ecommerce.entities.UserBilling;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface BillingAddressService {

	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
