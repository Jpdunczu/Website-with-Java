/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;


import Beans.Cart;
import Beans.Product;
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
public class CartDB {
    
    public static int insertCart(Cart cart, String sessionID, String productSize, String quantity) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query 
                = "INSERT INTO newProject.Cart (CartName,CartProducts,sessionID,productSize,quantity) " 
                + "VALUES (?, ?, ?, ?, ?);";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cart.getCartUser());
            ps.setString(2, String.valueOf(cart.getProductID()));
            ps.setString(3, sessionID);
            ps.setString(4, productSize);
            ps.setString(5, quantity);
            
            return ps.executeUpdate();
        } catch ( SQLException sqle ) {
            System.out.println(sqle);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static List<Cart> getCarts( String username ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Cart "
                + "WHERE cartName = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,username);
            
            rs = ps.executeQuery();
            
            List<Cart> carts = new ArrayList<>();
            while( rs.next() ) {
                String productID = (rs.getString("CartProducts"));
                Product product = ProductDB.getProduct(Integer.parseInt(productID));
                String quantity = (rs.getString("quantity"));
                String size = (rs.getString("productSize"));
                Cart cart = new Cart(quantity,product, size);
                carts.add(cart);
            }
            return carts;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static boolean cartItemExists( String username, int productID ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Cart "
                + "WHERE cartName = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                if(productID == ( rs.getInt( "cartProducts" ) )){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static boolean hasCart ( String username ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Cart "
                + "WHERE CartName = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                if(username.equals(rs.getString("CartName"))){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static int cartCount ( String username ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Cart "
                + "WHERE CartName = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            int count = 0;
            while( rs.next() ) {
                if( username.equals( rs.getString("CartName"))){
                    count++;
                }
            }
            return count;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static void removeItem ( int row ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM newProject.Cart " 
                + "WHERE idCart = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt( 1, row );
            
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static void updateCart ( String username, int productID, String productCount, String productSize ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Cart " 
                + "WHERE CartName = ?";
        String productCode = String.valueOf(productID);
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while( rs.next() ) {
                if( productCode.equals(rs.getString("CartProducts")) ) {
                    if(qtyCart(rs.getInt("idCart"), productCount) == 0){
                        System.out.println("updateCart QTY failed.");
                    }
                    if(sizeCart(rs.getInt("idCart"), productSize) == 0){
                        System.out.println("updateDart SIZE failed.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    
    public static int qtyCart ( int idCart, String productCount ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE newProject.Cart SET quantity = ? WHERE idCart = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(2, idCart);
            ps.setString(1, productCount);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
                
    }
    
    public static int sizeCart ( int idCart, String productSize ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE newProject.Cart SET productSize = ? WHERE idCart = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(2, idCart);
            ps.setString(1, productSize);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
                
    }
    
    public static int selectCart( String username, int productID ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.Cart "
                + "WHERE CartName = ?";
        String productCode = String.valueOf(productID);
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while( rs.next() ) {
                if( username.equals( rs.getString( "CartName" ) ) && productCode.equals( rs.getString( "CartProducts" ) ) ){
                int row = ( rs.getInt( "idCart" ) );
                return row;
                }
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e + " " + "This is selectCart");
            return 0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static Cart getCartsRetail( String username ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
             
        String query = "SELECT * FROM newProject.Cart "
                + "WHERE CartName = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1,username);
            
            rs = ps.executeQuery();
            Cart cart = null;
            double retail = 0.0;
            double cost = 0.0;
            String CartProducts = "";
            String quantity = "";
            while( rs.next() ) {
                CartProducts = (rs.getString("CartProducts"));
                quantity = rs.getString("quantity");
                cost += getCost(CartProducts) * Integer.valueOf(quantity);
                retail += getRetail(CartProducts) * Integer.valueOf(quantity);
            }
            cart = new Cart(username, cost, retail);
            return cart;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static double getRetail(String productCode){
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.product "
                + "WHERE productCode = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            
            rs = ps.executeQuery();
            if( rs.next()){
            double retail = rs.getDouble("productRetail");
            return retail;
            }
            return 0.0;
        } catch (SQLException e){
            System.out.println(e);
            return 0.0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static double getCost(String productCode){
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.product "
                + "WHERE productCode = ?";
        
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, productCode);
            
            rs = ps.executeQuery();
            if( rs.next() ){
            double cost = rs.getDouble("productCost");
            return cost;
            }
            return 0.0;
            
        } catch (SQLException e){
            System.out.println(e);
            return 0.0;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static void deleteCart( int idCart ){
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM newProject.Cart "
                + "WHERE idCart = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, idCart);
            
        } catch (SQLException e){
            System.out.println(e);
        } finally {
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
