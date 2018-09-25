package Ecommerce.service.impl;

import Ecommerce.entities.Payment;
import Ecommerce.entities.UserPayment;
import Ecommerce.service.def.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Transactional(rollbackFor = Exception.class)
    public Payment setByUserPayment(UserPayment userPayment, Payment payment) {

        payment.setType(userPayment.getType());
        payment.setHolderName(userPayment.getHolderName());
        payment.setCardNumber(userPayment.getCardNumber());
        payment.setExpiryMonth(userPayment.getExpiryMonth());
        payment.setExpiryYear(userPayment.getExpiryYear());
        payment.setCvc(userPayment.getCvc());

        return payment;
    }
}
