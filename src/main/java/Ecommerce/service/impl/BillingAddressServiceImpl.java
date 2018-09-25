package Ecommerce.service.impl;

import Ecommerce.entities.BillingAddress;
import Ecommerce.entities.UserBilling;
import Ecommerce.service.def.BillingAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class BillingAddressServiceImpl implements BillingAddressService {


    @Transactional(rollbackFor = Exception.class)
    public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {

        billingAddress.setBillingAddressName(userBilling.getUserBillingName());
        billingAddress.setBillingAddressStreetOne(userBilling.getUserBillingStreetOne());
        billingAddress.setBillingAddressStreetTwo(userBilling.getUserBillingStreetTwo());
        billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
        billingAddress.setBillingAddressState(userBilling.getUserBillingState());
        billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
        billingAddress.setBillingAddressZipcode(userBilling.getUserBillingZipcode());

        return billingAddress;
    }
}