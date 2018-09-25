package Ecommerce.service.impl;


import Ecommerce.entities.*;
import Ecommerce.repository.*;
import Ecommerce.service.def.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Chaklader on Oct, 2017
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;


    /*
    * method to get the passeword reset token('s)
    * */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    /*
    * create password reset tokens for the user
    * */
    @Transactional(rollbackFor = Exception.class)
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }


    /*
    * find the user with the name
    * */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    /*
    * find the user with the Id
    * */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public User findById(Long id) {
        return userRepository.findOne(id);
    }


    /*
    * find the user with the email
    * */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /*
    * create user with the specified set of the roles
    * */
    @Transactional(rollbackFor = Exception.class)
    public User createUser(User user, Set<UserRole> userRoles) {

        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
        } else {

            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            ShoppingCart shoppingCart = new ShoppingCart();

            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);

            user.setUserShippingList(new ArrayList<UserShipping>());
            user.setUserPaymentList(new ArrayList<UserPayment>());

            localUser = userRepository.save(user);
        }

        return localUser;
    }


    /*
    * save the user in the database
    * */
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        return userRepository.save(user);
    }


    /*
    * update the user billing information
    * */
    @Transactional(rollbackFor = Exception.class)
    public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {

        userPayment.setUser(user);
        userPayment.setUserBilling(userBilling);
        userPayment.setDefaultPayment(true);
        userBilling.setUserPayment(userPayment);
        user.getUserPaymentList().add(userPayment);
        save(user);
    }


    /*
    * update the user shipping info
    * */
    @Transactional(rollbackFor = Exception.class)
    public void updateUserShipping(UserShipping userShipping, User user) {

        userShipping.setUser(user);
        userShipping.setUserShippingDefault(true);
        user.getUserShippingList().add(userShipping);
        save(user);
    }


    /*
    * set the default payment info
    * */
    @Transactional(rollbackFor = Exception.class)
    public void setUserDefaultPayment(Long userPaymentId, User user) {

        List<UserPayment> userPaymentList = (List<UserPayment>) userPaymentRepository.findAll();

        for (UserPayment userPayment : userPaymentList) {
            if (userPayment.getId() == userPaymentId) {
                userPayment.setDefaultPayment(true);
                userPaymentRepository.save(userPayment);
            } else {
                userPayment.setDefaultPayment(false);
                userPaymentRepository.save(userPayment);
            }
        }
    }


    /*
    * set the default shipping info
    * */
    @Transactional(rollbackFor = Exception.class)
    public void setUserDefaultShipping(Long userShippingId, User user) {

        List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();

        for (UserShipping userShipping : userShippingList) {
            if (userShipping.getId() == userShippingId) {
                userShipping.setUserShippingDefault(true);
                userShippingRepository.save(userShipping);
            } else {
                userShipping.setUserShippingDefault(false);
                userShippingRepository.save(userShipping);
            }
        }
    }
}
