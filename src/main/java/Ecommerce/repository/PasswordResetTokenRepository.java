package Ecommerce.repository;


import Ecommerce.entities.PasswordResetToken;
import Ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.stream.Stream;


/**
 * Created by Chaklader on Oct, 2017
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(User user);

    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    @Modifying
    @Query("DELETE FROM PasswordResetToken prt WHERE prt.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
