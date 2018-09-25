package Ecommerce.entities;

import javax.persistence.*;

/**
 * Created by Chaklader on Oct, 2017
 */
@Entity
public class UserShipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userShippingName;

    private String userShippingStreetOne;

    private String userShippingStreetTwo;

    private String userShippingCity;

    private String userShippingState;

    private String userShippingCountry;

    private String userShippingZipcode;

    private boolean userShippingDefault;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserShippingName() {
        return userShippingName;
    }

    public void setUserShippingName(String userShippingName) {
        this.userShippingName = userShippingName;
    }

    public String getUserShippingStreetOne() {
        return userShippingStreetOne;
    }

    public void setUserShippingStreetOne(String userShippingStreetOne) {
        this.userShippingStreetOne = userShippingStreetOne;
    }

    public String getUserShippingStreetTwo() {
        return userShippingStreetTwo;
    }

    public void setUserShippingStreetTwo(String userShippingStreetTwo) {
        this.userShippingStreetTwo = userShippingStreetTwo;
    }

    public String getUserShippingCity() {
        return userShippingCity;
    }

    public void setUserShippingCity(String userShippingCity) {
        this.userShippingCity = userShippingCity;
    }

    public String getUserShippingState() {
        return userShippingState;
    }

    public void setUserShippingState(String userShippingState) {
        this.userShippingState = userShippingState;
    }

    public String getUserShippingCountry() {
        return userShippingCountry;
    }

    public void setUserShippingCountry(String userShippingCountry) {
        this.userShippingCountry = userShippingCountry;
    }

    public String getUserShippingZipcode() {
        return userShippingZipcode;
    }

    public void setUserShippingZipcode(String userShippingZipcode) {
        this.userShippingZipcode = userShippingZipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isUserShippingDefault() {
        return userShippingDefault;
    }

    public void setUserShippingDefault(boolean userShippingDefault) {
        this.userShippingDefault = userShippingDefault;
    }
}

