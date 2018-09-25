package Ecommerce.service.utils;

import Ecommerce.entities.Order;
import Ecommerce.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Locale;

/**
 * Created by Chaklader on Oct, 2017
 */
@Component
public class MailConstructor {

    @Autowired
    private Environment environment;

    @Autowired
    private TemplateEngine templateEngine;


    /*
    * method to create reset token email
    * */
    public SimpleMailMessage createResetTokenEmail(String contextPath, Locale locale,
                                                   String token, User user, String password) {

        String url = contextPath + "/newUser?token=" + token;
        String message = "\nPlease click on this link to verify your email and edit " +
                "your personal information. Your password is: \n" + password;

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(user.getEmail());
        email.setSubject("Chaklader's Book Store - New User");
        email.setText(url + message);
        email.setFrom(environment.getProperty("support.email"));

        return email;
    }

    /*
    * method to create the order confirmation email
    * */
    public MimeMessagePreparator createOrderConfirmationEmail(User user, Order order, Locale locale) {

        Context context = new Context();

        context.setVariable("order", order);
        context.setVariable("user", user);
        context.setVariable("cartItemList", order.getCartItemList());
        String text = templateEngine.process("orderConfirmationEmailTemplate", context);

        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper email = new MimeMessageHelper(mimeMessage);

                email.setTo(user.getEmail());
                email.setSubject("Order Confirmation - " + order.getOrderId());
                email.setText(text, true);
                email.setFrom(new InternetAddress("omi.chaklader@gmail.com"));
            }
        };

        return messagePreparator;
    }
}

