package Ecommerce.entities;

import javax.persistence.*;

/**
 * Created by Chaklader on Oct, 2017
 */
@Entity
public class UserBilling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userBillingName;

    private String userBillingStreetOne;

    private String userBillingStreetTwo;

    private String userBillingCity;

    private String userBillingState;

    private String userBillingCountry;

    private String userBillingZipcode;

    @OneToOne(cascade = CascadeType.ALL)
    private UserPayment userPayment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserBillingName() {
        return userBillingName;
    }

    public void setUserBillingName(String userBillingName) {
        this.userBillingName = userBillingName;
    }

    public String getUserBillingStreetOne() {
        return userBillingStreetOne;
    }

    public void setUserBillingStreetOne(String userBillingStreetOne) {
        this.userBillingStreetOne = userBillingStreetOne;
    }

    public String getUserBillingStreetTwo() {
        return userBillingStreetTwo;
    }

    public void setUserBillingStreetTwo(String userBillingStreetTwo) {
        this.userBillingStreetTwo = userBillingStreetTwo;
    }

    public String getUserBillingCity() {
        return userBillingCity;
    }

    public void setUserBillingCity(String userBillingCity) {
        this.userBillingCity = userBillingCity;
    }

    public String getUserBillingState() {
        return userBillingState;
    }

    public void setUserBillingState(String userBillingState) {
        this.userBillingState = userBillingState;
    }

    public String getUserBillingCountry() {
        return userBillingCountry;
    }

    public void setUserBillingCountry(String userBillingCountry) {
        this.userBillingCountry = userBillingCountry;
    }

    public String getUserBillingZipcode() {
        return userBillingZipcode;
    }

    public void setUserBillingZipcode(String userBillingZipcode) {
        this.userBillingZipcode = userBillingZipcode;
    }

    public UserPayment getUserPayment() {
        return userPayment;
    }

    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }
}

