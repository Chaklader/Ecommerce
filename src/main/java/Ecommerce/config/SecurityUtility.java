package Ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Chaklader on Nov, 2017
 */
@Component
public class SecurityUtility {

    private static final String SALT = "salt";

    public static String getStringGenerator() {

        String SALTCHARS = "";

        for (char c = 'a'; c <= 'z'; c++) {

            SALTCHARS += c;
        }

        SALTCHARS = SALTCHARS.toUpperCase();

        for (int j = 0; j <= 9; j++) {
            SALTCHARS += j;
        }

        return SALTCHARS;
    }


    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public static String randomPassword() {

        String SALTCHARS = getStringGenerator();

        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();

        while (builder.length() < 18) {

            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            builder.append(SALTCHARS.charAt(index));
        }

        String saltStr = builder.toString();
        return saltStr;
    }
}
