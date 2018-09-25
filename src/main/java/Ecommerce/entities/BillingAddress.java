package Ecommerce.entities;

import javax.persistence.*;

/**
 * Created by Chaklader on Oct, 2017
 */
@Entity
public class BillingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billingAddressId;

    private String BillingAddressName;

    private String BillingAddressStreetOne;

    private String BillingAddressStreetTwo;

    private String BillingAddressCity;

    private String BillingAddressState;

    private String BillingAddressCountry;

    private String BillingAddressZipcode;

    @OneToOne
    private Order order;

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public String getBillingAddressName() {
        return BillingAddressName;
    }

    public void setBillingAddressName(String billingAddressName) {
        BillingAddressName = billingAddressName;
    }

    public String getBillingAddressStreetOne() {
        return BillingAddressStreetOne;
    }

    public void setBillingAddressStreetOne(String billingAddressStreetOne) {
        BillingAddressStreetOne = billingAddressStreetOne;
    }

    public String getBillingAddressStreetTwo() {
        return BillingAddressStreetTwo;
    }

    public void setBillingAddressStreetTwo(String billingAddressStreetTwo) {
        BillingAddressStreetTwo = billingAddressStreetTwo;
    }

    public String getBillingAddressCity() {
        return BillingAddressCity;
    }

    public void setBillingAddressCity(String billingAddressCity) {
        BillingAddressCity = billingAddressCity;
    }

    public String getBillingAddressState() {
        return BillingAddressState;
    }

    public void setBillingAddressState(String billingAddressState) {
        BillingAddressState = billingAddressState;
    }

    public String getBillingAddressCountry() {
        return BillingAddressCountry;
    }

    public void setBillingAddressCountry(String billingAddressCountry) {
        BillingAddressCountry = billingAddressCountry;
    }

    public String getBillingAddressZipcode() {
        return BillingAddressZipcode;
    }

    public void setBillingAddressZipcode(String billingAddressZipcode) {
        BillingAddressZipcode = billingAddressZipcode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

