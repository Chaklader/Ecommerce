package Ecommerce.entities;

import javax.persistence.*;
import java.util.Calendar;

import java.util.Date;

/**
 * Created by Chaklader on Sep, 2017
 */
@Entity
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passwordResetTokenId;

    private static final int EXPIRATION = 60 * 24;

    private String token;

    @JoinColumn(nullable = false, name = "user_id")
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    private Date expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(final String token, final User user) {

        super();

        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {

        final Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);

        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public Long getPasswordResetTokenId() {
        return passwordResetTokenId;
    }

    public void setPasswordResetTokenId(Long passwordResetTokenId) {
        this.passwordResetTokenId = passwordResetTokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public static int getExpiration() {
        return EXPIRATION;
    }
}
