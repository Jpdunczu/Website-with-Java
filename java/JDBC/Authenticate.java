/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import Beans.User;
import Security.PasswordUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joshuaduncan
 */
public class Authenticate {
    Authenticate(){}
    public static Boolean authenticator( User user ) {
        List<User> users = UserDB.authenticateUsers();
        Boolean status = false;
        
        for( User u : users ) {
            if( u.getUsername().equals(user.getUsername() ) &&
                    u.getPassword().equals( PasswordUtil.hashPassword(user.getPassword()) + u.getSalt() ) ){
                status = true;
                break;
            }
        }
        return status;
    }
}
