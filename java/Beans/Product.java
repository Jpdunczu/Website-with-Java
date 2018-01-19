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
public class Product implements Serializable {
    
    private int productID;
    private String productName;
    private String productDesc;
    private String productImage;
    private double productCost;
    private double productRetail;
    private String sale;
    private double salePrice;
    private String productType;

    public Product() {
    }

    public Product(int productID, String productName, String productDesc, String productImage, double cost, double retail) {
        this.productID = productID;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productCost = cost;
        this.productRetail = retail;
    }

    public Product(int productID, String productName, String productDesc, String productImage, double productCost, double productRetail, String sale, double salePrice, String productType) {
        this.productID = productID;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImage = productImage;
        this.productCost = productCost;
        this.productRetail = productRetail;
        this.sale = sale;
        this.salePrice = salePrice;
        this.productType = productType;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public double getProductCost() {
        return productCost;
    }

    public double getProductRetail() {
        return productRetail;
    }
    
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
    
    
    
}
