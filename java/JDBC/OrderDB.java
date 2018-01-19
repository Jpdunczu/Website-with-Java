/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Beans.Cart;
import Beans.Customer;
import Beans.Order;
import Beans.User;
import static JDBC.CartDB.freeConnection;
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
public class OrderDB {
    
    public static int insertCustomer( Customer customer ){
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "INSERT INTO newProject.Customer (name,email,phone,billingAddress,orderNumber,username) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPhone());
            ps.setString(4, customer.getBillingAddress());
            ps.setInt(5,customer.getOrderNumber());
            ps.setString(6, customer.getUsername());
            return ps.executeUpdate();
            
        } catch ( SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static Customer updateCustomer( String username, int orderNumber ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE newProject.Customer SET orderNumber = ? WHERE username = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderNumber);
            ps.setString(2, username);
            int check = ps.executeUpdate();
            if( check == 0 ){
                System.out.println("error in updateCustomer in OrderDB");
            }
            return getCustomer(username);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static boolean insertOrder( Order order ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query 
                = "INSERT INTO newProject.Order (payment,shippingMethod,CartName,sessionID,orderDate,orderTotal,orderStatus,shippingAddress) " 
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, order.getPayment());
            ps.setString(2, order.getShippingMethod());
            ps.setString(3, order.getCartName());
            ps.setString(4, order.getSessionID());
            ps.setString(5, order.getOrderDate());
            ps.setDouble(6, order.getOrderTotal());
            ps.setString(7, order.getOrderStatus());
            ps.setString(8, order.getShippingAddress());
            int insert = ps.executeUpdate();
            return true;
        } catch ( SQLException sqle ) {
            System.out.println(sqle);
            return false;
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static Customer getCustomer ( String username ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Customer customer;
        
        String query = "SELECT * FROM newProject.Customer " 
                + "WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if( rs.next() ){
                if( username.equals( rs.getString("username") ) ) {
                    String name = rs.getString(("name"));
                    String address = rs.getString("billingAddress");
                    String phone = rs.getString("phone");
                    int orderNumber = rs.getInt("orderNumber");
                    customer = new Customer(address,name,phone,orderNumber,username);
                    return customer;
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            freeConnection(connection);
        }
    }
    
    public static Order getOrder ( int orderNumber ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Order order;
        
        String query = "SELECT * FROM newProject.Order " 
                + "WHERE orderNumber = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderNumber);
            rs = ps.executeQuery();
            if( rs.next() ){
                if( orderNumber == rs.getInt("orderNumber") ) {
                    String CartName = rs.getString("CartName");
                    double orderTotal = rs.getDouble("orderTotal");
                    order = new Order(CartName,orderNumber,orderTotal);
                    return order;
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            freeConnection(connection);
        }
    }
    
    public static Customer getFullCustomer ( String username ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Customer customer;
        
        String query = "SELECT * FROM newProject.Customer " 
                + "WHERE username = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if( rs.next() ){
                if( username.equals( rs.getString("username") ) ) {
                    String name = rs.getString(("name"));
                    String address = rs.getString("billingAddress");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    customer = new Customer(name,email,phone,address,username);
                    return customer;
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            freeConnection(connection);
        }
    }
    
    public static Order getFullOrder ( int orderNumber ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Order order;
        
        String query = "SELECT * FROM newProject.Order " 
                + "WHERE orderNumber = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderNumber);
            rs = ps.executeQuery();
            if( rs.next() ){
                if( orderNumber == rs.getInt("orderNumber") ) {
                    String shippingName = rs.getString("shippingName");
                    String shippingMethod = rs.getString("shippingMethod");
                    String CartName = rs.getString("CartName");
                    String orderDate = rs.getString("orderDate");
                    String orderStatus = rs.getString("orderStatus");
                    String shippingAddress = rs.getString("shippingAddress");
                    double orderTotal = rs.getDouble("orderTotal");
                    
                    order = new Order(shippingMethod,CartName,orderNumber,orderDate,orderTotal,orderStatus,shippingAddress,shippingName);
                    return order;
                }
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            freeConnection(connection);
        }
    }
    
    public static int getOrderNumber ( String sessionID ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Order "
                + "WHERE sessionID = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, sessionID);
            rs = ps.executeQuery();
            if( rs.next() ){
                if( sessionID.equals(rs.getString("sessionID"))){
                    return rs.getInt("orderNumber");
                }
            }
            return 0;
        } catch ( SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }           
    }
    
    public static void deleteOrder ( Order order ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE * FROM newProject.Order "
                + "WHERE orderNumber = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, order.getOrderNumber());
            int del = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static void deleteCust ( Customer customer ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE * FROM newProject.Customer "
                + "WHERE username = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getUsername());
            int cus = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static boolean hasOrderHistory(String username) {
       Connection connection = ConnectionUtil.getConnection();
       PreparedStatement ps = null;
       ResultSet rs = null;
       
       String query = "SELECT * FROM newProject.Order " 
               + "WHERE CartName = ?";
       
       try {
           ps = connection.prepareStatement(query);
           ps.setString(1, username);
           rs = ps.executeQuery();
           if( rs.next() ) {
               if( username.equals( rs.getString("CartName"))) {
                   return true;
               }
           }
           return false;
       } catch (SQLException e) {
           System.out.println(e);
           return false;
       } finally {
           DBUtil.closePreparedStatement(ps);
           DBUtil.closeResultSet(rs);
           freeConnection(connection);
       }
    }
    
    public static List<Order> getOrderHistory( String username ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Order " 
                + "WHERE CartName = ?";
        Order order;
        List<Order> orders = new ArrayList<>();
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while( rs.next() ) {
                if( username.equals( rs.getString("CartName") ) ) {
                    int orderNumber = rs.getInt("orderNumber");
                    String shippingMethod = "";
                    if( rs.getString("shippingMethod").equals("0.00") ) 
                        shippingMethod = "Standard";
                    else if ( rs.getString("shippingMethod").equals("5.99") )
                        shippingMethod = "Expedited";
                    else if ( rs.getString("shippingMethod").equals("12.99") )
                        shippingMethod = "Rush";
                    else if ( rs.getString("shippingMethod").equals("19.99") )
                        shippingMethod = "Overnight";
                    String CartName = rs.getString("CartName");
                    String orderDate = rs.getString("orderDate");
                    double orderTotal = rs.getDouble("orderTotal");
                    String orderStatus = rs.getString("orderStatus");
                    String shippingAddress = rs.getString("shippingAddress");
                    String shippingName = rs.getString("shippingName");
                    order = new Order(shippingMethod,CartName,orderNumber,orderDate,orderTotal,orderStatus,shippingAddress,shippingName);
                    List<Cart> carts = new ArrayList<>();
                    carts = CartDB.getCarts(username);
                    order.setCart(carts);
                    orders.add(order);
                }
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            freeConnection(connection);
        }
    }
    
    public static void getCartID( Order order ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Cart "
                + "WHERE CartName = ?";
        String orderNumber = String.valueOf(order.getOrderNumber());
        String cartName = order.getCartName();
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, cartName);
            rs = ps.executeQuery();
            while( rs.next() ) {
                if( cartName.equals( rs.getString("CartName") ) ) {
                    
                    int idCart = rs.getInt("idCart");
                    updateCartOrder( idCart, orderNumber );
                }
            }
        } catch ( SQLException e ) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            freeConnection(connection);
        }
    }
    
    public static void updateCartOrder ( int idCart, String orderNumber ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        System.out.println("updateCartOrder");
        String query = "UPDATE newProject.Cart SET CartName = ? WHERE idCart = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(2, idCart);
            ps.setString(1, orderNumber);
            int uco = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
                
    }
    public static List<Customer> getCustomers() {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Customer ";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Customer customer;
            List<Customer> customers = new ArrayList<>();
            while( rs.next() ) {
                    String name = rs.getString(("name"));
                    String address = rs.getString("billingAddress");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String username = rs.getString("username");
                    customer = new Customer(name,email,phone,address,username);
                    List<Order> orders = OrderDB.getOrderHistory(username);
                    customer.setOrders(orders);
                    customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static List<Order> getOrders() {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Order ";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            Order order;
            List<Order> orders = new ArrayList<>();
            while( rs.next() ) {
                    String shippingMethod = "";
                    if( rs.getString("shippingMethod").equals("0.00") ) 
                        shippingMethod = "Standard";
                    else if ( rs.getString("shippingMethod").equals("5.99") )
                        shippingMethod = "Expedited";
                    else if ( rs.getString("shippingMethod").equals("12.99") )
                        shippingMethod = "Rush";
                    else if ( rs.getString("shippingMethod").equals("19.99") )
                        shippingMethod = "Overnight";
                    String CartName = rs.getString("CartName");
                    String orderDate = rs.getString("orderDate");
                    double orderTotal = rs.getDouble("orderTotal");
                    String orderStatus = rs.getString("orderStatus");
                    int orderNumber = rs.getInt("orderNumber");
                    String shippingAddress = rs.getString("shippingAddress");
                    String shippingName = rs.getString("shippingName");
                    order = new Order(shippingMethod,CartName,orderNumber,orderDate,orderTotal,orderStatus,shippingAddress,shippingName);
                    List<Cart> carts = CartDB.getCarts(String.valueOf(orderNumber));
                    Customer customer = OrderDB.getFullCustomer(CartName);
                    order.setCustomer(customer);
                    order.setCart(carts);
                    orders.add(order);
            }
            return orders;
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
