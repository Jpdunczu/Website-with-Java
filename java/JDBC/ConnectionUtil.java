/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author joshuaduncan
 */
public class ConnectionUtil {
    
    public static Connection getConnection(){
        Connection conn = null;
        try{
            String url = "jdbc:mysql://localhost:3306/newProject";
            String username = "jpdunczu";
            String password = "Pemayeshedorje108";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        }catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void freeConnection (Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
