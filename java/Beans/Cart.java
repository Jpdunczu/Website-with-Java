/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;

/**
 *
 * @author joshuaduncan
 */
public class Cart implements Serializable {
    
    private String cartUser;
    private String productCount;
    private Product product;
    private int productID;
    private String size;
    private double cartCost;
    private double cartRetail;

    
    public Cart(){}
    
    public Cart(String cartUser, String productCount, int productID ){
        this.cartUser = cartUser;
        this.productCount = productCount;
        this.productID = productID;        
    }
    
    public Cart(String cartUser, String productCount, Product product) {
        this.cartUser = cartUser;
        this.productCount = productCount;
        this.product = product;
    }

    public Cart(String productCount, Product product, String size) {
        this.productCount = productCount;
        this.product = product;
        this.size = size;
        this.cartCost = Integer.parseInt(productCount) * product.getProductRetail();
    }

    public Cart(String cartUser, double cartCost, double cartRetail) {
        this.cartUser = cartUser;
        this.cartCost = cartCost;
        this.cartRetail = cartRetail;
    }
    
    
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    public String getCartUser() {
        return cartUser;
    }

    public void setCartUser(String cartUser) {
        this.cartUser = cartUser;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getCartCost() {
        return cartCost;
    }

    public void setCartCost(double cartCost) {
        this.cartCost = cartCost;
    }

    public double getCartRetail() {
        return (cartRetail*100)/100;
    }

    public void setCartRetail(double cartRetail) {
        this.cartRetail = cartRetail;
    }
    
    
    
}
