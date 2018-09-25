package Ecommerce.service.impl;

import Ecommerce.entities.ShippingAddress;
import Ecommerce.entities.UserShipping;
import Ecommerce.service.def.ShippingAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {


    @Transactional(rollbackFor = Exception.class)
    public ShippingAddress setByUserShipping(UserShipping userShipping,
                                             ShippingAddress shippingAddress) {

        shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
        shippingAddress.setShippingAddressStreetOne(userShipping.getUserShippingStreetOne());
        shippingAddress.setShippingAddressStreetTwo(userShipping.getUserShippingStreetTwo());
        shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
        shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
        shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
        shippingAddress.setShippingAddressZipcode(userShipping.getUserShippingZipcode());

        return shippingAddress;
    }
}
