/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author joshuaduncan
 */
public class Order implements Serializable {
    
    private String payment;
    private String shippingMethod;
    private String cartName;
    private String sessionID;
    private int orderNumber;
    private String orderDate;
    private double orderTotal;
    private String orderStatus;
    private String shippingAddress;
    private String shippingName;
    List<Cart> cart = new ArrayList<>();
    private Customer customer;
    
    public Order() {
    }

    public Order(String cartName, int orderNumber, double orderTotal) {
        this.cartName = cartName;
        this.orderNumber = orderNumber;
        this.orderTotal = orderTotal;
    }
    
    public Order(String shippingMethod, String cartName, String sessionID, int orderNumber, String orderDate ) {
        this.shippingMethod = shippingMethod;
        this.cartName = cartName;
        this.sessionID = sessionID;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        
    }

    public Order(String cartName, int orderNumber, String orderDate, double orderTotal, String orderStatus) {
        this.cartName = cartName;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
    }
    
    
    public Order(String payment, String shippingMethod, String cartName, String sessionID, String orderDate, double orderTotal, String orderStatus, String shippingAdress) {
        this.payment = payment;
        this.shippingMethod = shippingMethod;
        this.cartName = cartName;
        this.sessionID = sessionID;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
        this.shippingAddress = shippingAddress;
    }

    public Order(String payment, String shippingMethod, String cartName, String sessionID, int orderNumber, String orderDate, double orderTotal, String orderStatus, String shippingAddress) {
        this.payment = payment;
        this.shippingMethod = shippingMethod;
        this.cartName = cartName;
        this.sessionID = sessionID;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
        this.shippingAddress = shippingAddress;
    }

    public Order(String shippingMethod, String cartName, int orderNumber, String orderDate, double orderTotal, String orderStatus,String shippingAddress,String shippingName) {
        
        this.shippingMethod = shippingMethod;
        this.cartName = cartName;
        this.orderNumber = orderNumber;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
        this.shippingAddress = shippingAddress;
        this.shippingName = shippingName;
    }

    public String getShippingName(){
        return shippingName;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Cart> getCarts() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
    
}
