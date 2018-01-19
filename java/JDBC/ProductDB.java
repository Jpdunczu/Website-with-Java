/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Beans.Product;
import Beans.User;
import static JDBC.UserDB.freeConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joshuaduncan
 */
public class ProductDB {
    
    public static Product getProduct( int productCode ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Product "
                + "WHERE productCode = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,productCode);
            
            rs = ps.executeQuery();
            Product product = null;
            while( rs.next() ) {
                String productName = (rs.getString("productName"));
                String productDescription = (rs.getString("productDescription"));
                String productImage = (rs.getString("productImage"));
                double productCost = (rs.getDouble("productCost"));
                double productRetail = (rs.getDouble("productRetail"));
                product = new Product(productCode,productName,productDescription,productImage,productCost,productRetail);
            }
            return product;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static List<Product> getProducts( String productType ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Product "
                + "WHERE productType = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,productType);
            
            rs = ps.executeQuery();
            Product product = null;
            List<Product> products = new ArrayList<>();
            while( rs.next() ) {
                String productName = (rs.getString("productName"));
                String productDescription = (rs.getString("productDescription"));
                int productCode = (rs.getInt("productCode"));
                String productImage = (rs.getString("productImage"));
                double productCost = (rs.getDouble("productCost"));
                double productRetail = (rs.getDouble("productRetail"));
                product = new Product(productCode,productName,productDescription,productImage,productCost,productRetail);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static List<Product> getAllProducts() {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Product ";
        
        try {
            ps = connection.prepareStatement(query);
            
            rs = ps.executeQuery();
            Product product = null;
            List<Product> products = new ArrayList<>();
            while( rs.next() ) {
                String productName = (rs.getString("productName"));
                String productDescription = (rs.getString("productDescription"));
                int productCode = (rs.getInt("productCode"));
                String productImage = (rs.getString("productImage"));
                double productCost = (rs.getDouble("productCost"));
                double productRetail = (rs.getDouble("productRetail"));
                String productType = (rs.getString("productType"));
                String sale = rs.getString("sale");
                double salePrice = rs.getDouble("salePrice");
                
                product = new Product(productCode,productName,productDescription,productImage,productCost,productRetail,sale,salePrice,productType);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
     public static List<Product> getSales( String sale ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Product "
                + "WHERE sale = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,sale);
            
            rs = ps.executeQuery();
            Product product = null;
            List<Product> products = new ArrayList<>();
            while( rs.next() ) {
                String productName = (rs.getString("productName"));
                String productDescription = (rs.getString("productDescription"));
                int productCode = (rs.getInt("productCode"));
                String productImage = (rs.getString("productImage"));
                double productCost = (rs.getDouble("productCost"));
                double productRetail = (rs.getDouble("productRetail"));
                product = new Product(productCode,productName,productDescription,productImage,productCost,productRetail);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
     
     
    public static void freeConnection (Connection c) {
        try {
            c.close();
        } catch (SQLException | NullPointerException e) {
            System.out.println(e);
        }
    }
}
