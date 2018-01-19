/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Beans.User;
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
public class UserDB {
    
    public static int insert(User user) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query 
                = "INSERT INTO newProject.User (firstname,lastname,email,username,password, salt) " 
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getUsername());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getSalt());
            return ps.executeUpdate();
        } catch ( SQLException sqle ) {
            System.out.println(sqle);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static int updateMailing(String email) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE newProject.User SET " 
                + "WHERE email = ?, "
                + "emailList = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, "1");
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    
    public static int update(User user) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "UPDATE newProject.User SET " 
                + "firstName = ?, "
                + "lastName = ?, "
                + "WHERE email = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static boolean usernameExists (String username) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.User "
                + "WHERE username = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                if(username.equals(rs.getString("username"))){
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
    
    public static boolean isAdmin ( String username ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT username, admin FROM newProject.User "
                + "WHERE username = ?";
        
        
        try {
            
            ps = connection.prepareStatement( query );
            ps.setString(1, username);
            rs = ps.executeQuery();
            while( rs.next() ){
                if(username.equals(rs.getString("username")) && rs.getString("admin").equals("1") ){
                    return true;
                }
            }
            return false;
            
        } catch (SQLException | NullPointerException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static boolean emailExists ( String email ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT email FROM newProject.User "
                + "WHERE email = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while( rs.next() ){
                if(email.equals(rs.getString("email"))){
                    return true;
                }
            }
            return false;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    
    public static User selectUser( String username ) {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.User "
                + "WHERE username = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            User user = null;
            if( rs.next() ) {
                
                String firstName = (rs.getString("firstName"));
                String lastName = (rs.getString("lastName"));
                String email = (rs.getString("email"));
                String password = (rs.getString("password"));
                username = rs.getString("username");
                user = new User(firstName,lastName,email,username,password);
             
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static List<User> authenticateUsers() {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.User ";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            User user = null;
            List<User> users = new ArrayList<>();
            while( rs.next() ) {
                
                String username = (rs.getString("username"));
                String password = (rs.getString("password"));
                String salt = (rs.getString("salt"));
                user = new User(username,password,salt);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
     
    public static List<User> getAdminUsers() {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.User ";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            User user = null;
            List<User> users = new ArrayList<>();
            while( rs.next() ) {
                int admin = rs.getInt("admin");
                if( admin == 0){
                    String firstName = (rs.getString("firstName"));
                    String lastName = (rs.getString("lastName"));
                    String email = (rs.getString("email"));
                    String username = (rs.getString("username"));
                    int emailList = rs.getInt("emailList");
                    String customer = rs.getString("customer");
                    user = new User(username,firstName,lastName,email,emailList,customer);
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static List<User> getUsers() {
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM newProject.User ";
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            User user = null;
            List<User> users = new ArrayList<>();
            while( rs.next() ) {
                int admin = rs.getInt("admin");
                if( admin == 0){
                    String firstName = (rs.getString("firstName"));
                    String lastName = (rs.getString("lastName"));
                    String email = (rs.getString("email"));
                    String password = (rs.getString("password"));
                    String username = (rs.getString("username"));
                    user = new User(firstName,lastName,email,username,password);
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            freeConnection(connection);
        }
    }
    
    public static int delete( String email ){
        Connection connection = ConnectionUtil.getConnection();
        PreparedStatement ps = null;
        
        String query = "DELETE FROM newProject.User "
                + "WHERE email = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            
            return ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
            return 0;
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
