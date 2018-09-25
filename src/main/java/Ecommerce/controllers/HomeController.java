package Ecommerce.controllers;

import Ecommerce.config.SecurityUtility;
import Ecommerce.entities.*;
import Ecommerce.service.def.*;
import Ecommerce.service.impl.UserSecurityService;
import Ecommerce.service.utils.MailConstructor;
import Ecommerce.service.utils.USStatesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;


/**
 * Created by Chaklader on Sep, 2017
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private UserShippingService userShippingService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String index() {
        return "index";
    }

    // hours info for the shop
    @RequestMapping("/hours")
    public String hours() {
        return "hours";
    }

    // FAQ info for the shop
    @RequestMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping(value = "login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "account";
    }

    @PostMapping(value = "/newUser")
    public String newUserPost(HttpServletRequest httpServletRequest, @ModelAttribute("email") String userEmail,
                              @ModelAttribute("username") String userName, Model model) throws Exception {

        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", userName);

        if (userService.findByUsername(userName) != null) {
            model.addAttribute("usernameExists", true);
            return "account";
        }

        if (userService.findByEmail(userEmail) != null) {
            model.addAttribute("emailExists", true);
            return "account";
        }

        User user = new User();
        user.setUsername(userName);
        user.setUserEmail(userEmail);

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        // set the role for the user
        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");

        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        // set the http url for the user post
        String httpUrl = "http://" + httpServletRequest.getServerName() + ":" +
                httpServletRequest.getServerPort() +
                httpServletRequest.getContextPath();

        SimpleMailMessage email = mailConstructor.createResetTokenEmail(httpUrl,
                httpServletRequest.getLocale(), token, user, password);

        mailSender.send(email);

        model.addAttribute("emailSent", "true");
        model.addAttribute("orderList", user.getOrderList());

        return "account";
    }


    @GetMapping(value = "newUser")
    public String newUser(Locale locale, @RequestParam("token") String token, Model model) {

        PasswordResetToken resetToken = userService.getPasswordResetToken(token);

        if (resetToken == null) {
            String message = "Invalid token!";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }

        User user = resetToken.getUser();
        String username = user.getUsername();

        // get the user details from the security service
        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // add the attributes to the modal
        model.addAttribute("user", user);
        model.addAttribute("classActiveEdit", true);

        return "profile";
    }


    /*
    * method to set the set the new password
    * if the current one is forgotten
    * */
    @GetMapping(value = "forgetPassword")
    public String forgetPassword(HttpServletRequest httpServletRequest,
                                 @ModelAttribute("email") String email, Model model) {

        model.addAttribute("classActiveForgetPassword", true);
        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("emailNotExist", true);
            return "account";
        }

        // email exist, now set the new password
        String password = SecurityUtility.randomPassword();
        String entryptedPassword = SecurityUtility.passwordEncoder().encode(password);

        user.setPassword(entryptedPassword);
        userService.save(user);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String httpUrlAddress = "http://" + httpServletRequest.getServerName() + ":" +
                httpServletRequest.getServerPort() + httpServletRequest.getContextPath();

        SimpleMailMessage newEmail = mailConstructor.createResetTokenEmail(httpUrlAddress,
                httpServletRequest.getLocale(), token, user, password);

        mailSender.send(newEmail);
        model.addAttribute("forgetPasswordEmailSent", true);
        return "account";
    }


    // we need to be log in to see the profile info
    @RequestMapping(value = "profile")
    public String getUserProfile(Model model, Principal principal) {

        // get the user from the name
        User user = userService.findByUsername(principal.getName());

        // set the user attributes
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());


        // set the user shipping attributes
        UserShipping userShipping = new UserShipping();

        model.addAttribute("userShipping", userShipping);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("listOfShippingAddresses", true);

        List<String> stateList = USStatesInfo.listOfUSStatesCode;
        Collections.sort(stateList);

        model.addAttribute("stateList", stateList);
        model.addAttribute("classActiveEdit", true);

        return "profile";
    }


    // get the list of all the credit cards
    @RequestMapping(value = "listOfCreditCards")
    public String listOfCreditCards(Model model, Principal principal, HttpServletRequest request) {

        // get the user from the name
        User user = userService.findByUsername(principal.getName());


        // add all the attributes for the user
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "profile";
    }


    // get the list of all the shipping addresses
    @RequestMapping(value = "listOfShippingAddresses")
    public String listOfShippingAddresses(Model model, Principal principal,
                                          HttpServletRequest request) {

        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "myProfile";
    }


    // method to add new credit card for te user
    @RequestMapping(value = "addNewCreditCard")
    public String addNewCreditCard(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        // add all the attributes for the user
        model.addAttribute("user", user);
        model.addAttribute("addNewCreditCard", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        UserBilling userBilling = new UserBilling();
        UserPayment userPayment = new UserPayment();

        // add the user billing and payment info
        model.addAttribute("userBilling", userBilling);
        model.addAttribute("userPayment", userPayment);

        List<String> stateList = USStatesInfo.listOfUSStatesCode;
        Collections.sort(stateList);

        model.addAttribute("stateList", stateList);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());

        // finally, return the profile
        return "profile";
    }


    // add new shipping address for the user
    @RequestMapping(value = "addNewShippingAddress")
    public String addNewShippingAddress(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());

        // add the attributes for the user
        model.addAttribute("user", user);
        model.addAttribute("addNewShippingAddress", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);


        UserShipping userShipping = new UserShipping();

        model.addAttribute("userShipping", userShipping);

        List<String> stateList = USStatesInfo.listOfUSStatesCode;
        Collections.sort(stateList);

        model.addAttribute("stateList", stateList);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("orderList", user.getOrderList());

        return "profile";
    }


    @PostMapping(value = "addNewCreditCard")
    public String addNewCreditCard(@ModelAttribute("userPayment") UserPayment userPayment,
                                   @ModelAttribute("userBilling") UserBilling userBilling,
                                   Principal principal, Model model) {

        // find the user by the name
        User user = userService.findByUsername(principal.getName());
        userService.updateUserBilling(userBilling, userPayment, user);

        // add all the modal attributes for the user
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPaymentList());
        model.addAttribute("userShippingList", user.getUserShippingList());
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("orderList", user.getOrderList());

        return "profile";
    }


    /*
    * method to update and provide comprehensive
    * info for the the user
    * */
    @PostMapping(value = "updateUserInfo")
    public String updateUserInformation(Model model, @ModelAttribute("user") User user,
                                        @ModelAttribute("newPassword") String newPassword) throws Exception {

        User currentUserGetById = userService.findById(user.getUserId());

        if (currentUserGetById == null) {
            throw new Exception("The user is not found!");
        }

        // check if the email already exists
        if (userService.findByEmail(user.getEmail()) != null) {

            if (userService.findByEmail(user.getEmail()).getUserId() != currentUserGetById.getUserId()) {
                model.addAttribute("emailExists", true);
                return "profile";
            }
        }

        // check username already exists
        if (userService.findByUsername(user.getUsername()) != null) {

            if (userService.findByUsername(user.getUsername()).getUserId() != currentUserGetById.getUserId()) {
                model.addAttribute("usernameExists", true);
                return "profile";
            }
        }

        // the email and the username is not already
        // exists so, we can upadate the user information
        if (newPassword != null || !newPassword.isEmpty() || !newPassword.equals("")) {

            String currentUserPassword = currentUserGetById.getPassword();
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();

            // return true if the raw password, after encoding, matches the encoded password from storage
            // boolean matches(CharSequence rawPassword, String encodedPassword)
            if (passwordEncoder.matches(user.getPassword(), currentUserPassword)) {

                // we need to set the password to the user retained by Id
                currentUserGetById.setPassword(passwordEncoder.encode(newPassword));
            }

            // the password doesnt match and hence we will return to the profile again
            else {
                model.addAttribute("incorrectPassword", true);
                return "profile";
            }
        }

        currentUserGetById.setUsername(user.getUsername());
        currentUserGetById.setFirstname(user.getFirstname());
        currentUserGetById.setLastname(user.getLastname());
        currentUserGetById.setUserEmail(user.getEmail());

        // all set, now persist the info to the database
        userService.save(currentUserGetById);


        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUserGetById);
        model.addAttribute("classActiveEdit", true);

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("listOfCreditCards", true);

        // get the user details for the user in concern
        UserDetails userDetails = userSecurityService.loadUserByUsername(currentUserGetById.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        model.addAttribute("orderList", user.getOrderList());

        return "profile";
    }
}
