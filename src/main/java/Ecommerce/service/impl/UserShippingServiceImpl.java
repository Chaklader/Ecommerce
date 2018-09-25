package Ecommerce.service.impl;

import Ecommerce.entities.UserShipping;
import Ecommerce.repository.UserShippingRepository;
import Ecommerce.service.def.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class UserShippingServiceImpl implements UserShippingService {

    @Autowired
	private UserShippingRepository userShippingRepository;
		
	public UserShipping findById(Long id) {
		return userShippingRepository.findOne(id);
	}
	
	public void removeById(Long id) {
		userShippingRepository.delete(id);
	}
}