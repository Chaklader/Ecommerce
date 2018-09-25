package Ecommerce.service.def;

import Ecommerce.entities.Payment;
import Ecommerce.entities.UserPayment;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface PaymentService {

	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
