/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joshuaduncan
 */
public class Customer implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String billingAddress;
    private String username;
    private int orderNumber;
    private List<Order> orders;
    
    public Customer() {
    }

    public Customer(String name, String email, String phone, String billingAddress, String username) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingAddress = billingAddress;
        this.orderNumber = 0;
        this.username = username;
    }

    public Customer(String billingAddress, String name, String phone, int orderNumber, String username) {
        this.billingAddress = billingAddress;
        this.name = name;
        this.phone = phone;
        this.orderNumber = orderNumber;
        this.username = username;
    }
    
    public Customer(String name, String email, String phone, String billingAddress, String username, int orderNumber) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.billingAddress = billingAddress;
        this.username = username;
        this.orderNumber = orderNumber;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    } 
    
    public int getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber( int orderNumber ) {
        this.orderNumber = orderNumber;
    }   
}
