package Ecommerce.service.def;

import Ecommerce.entities.UserPayment;

/**
 * Created by Chaklader on Oct, 2017
 */
public interface UserPaymentService {

    UserPayment findById(Long id);

    void removeById(Long id);
}