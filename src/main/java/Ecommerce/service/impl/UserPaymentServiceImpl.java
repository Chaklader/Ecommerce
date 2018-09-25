package Ecommerce.service.impl;

import Ecommerce.entities.UserPayment;
import Ecommerce.repository.UserPaymentRepository;
import Ecommerce.service.def.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class UserPaymentServiceImpl implements UserPaymentService {

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UserPayment findById(Long id) {
        return userPaymentRepository.findOne(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        userPaymentRepository.delete(id);
    }
} 
