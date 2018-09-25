package Ecommerce.entities;

import javax.persistence.*;

/**
 * Created by Chaklader on Oct, 2017
 */
@Entity
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ShippingAddressName;

    private String ShippingAddressStreetOne;

    private String ShippingAddressStreetTwo;

    private String ShippingAddressCity;

    private String ShippingAddressState;

    private String ShippingAddressCountry;

    private String ShippingAddressZipcode;

    @OneToOne
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingAddressName() {
        return ShippingAddressName;
    }

    public void setShippingAddressName(String shippingAddressName) {
        ShippingAddressName = shippingAddressName;
    }

    public String getShippingAddressStreetOne() {
        return ShippingAddressStreetOne;
    }

    public void setShippingAddressStreetOne(String shippingAddressStreetOne) {
        ShippingAddressStreetOne = shippingAddressStreetOne;
    }

    public String getShippingAddressStreetTwo() {
        return ShippingAddressStreetTwo;
    }

    public void setShippingAddressStreetTwo(String shippingAddressStreetTwo) {
        ShippingAddressStreetTwo = shippingAddressStreetTwo;
    }

    public String getShippingAddressCity() {
        return ShippingAddressCity;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        ShippingAddressCity = shippingAddressCity;
    }

    public String getShippingAddressState() {
        return ShippingAddressState;
    }

    public void setShippingAddressState(String shippingAddressState) {
        ShippingAddressState = shippingAddressState;
    }

    public String getShippingAddressCountry() {
        return ShippingAddressCountry;
    }

    public void setShippingAddressCountry(String shippingAddressCountry) {
        ShippingAddressCountry = shippingAddressCountry;
    }

    public String getShippingAddressZipcode() {
        return ShippingAddressZipcode;
    }

    public void setShippingAddressZipcode(String shippingAddressZipcode) {
        ShippingAddressZipcode = shippingAddressZipcode;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

