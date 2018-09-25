package Ecommerce.service.def;

import Ecommerce.entities.UserShipping;

/**
 * Created by Chaklader on Oct, 2017
 */
public interface UserShippingService {

    UserShipping findById(Long id);

    void removeById(Long id);
}