/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

/**
 *
 * @author joshuaduncan
 */
public class PasswordCheck {
    
    private static StringBuilder passwordError = new StringBuilder();
    
    public static StringBuilder PWCheck( String password, String firstName, String lastName ){
        
        if( password.length() < 8 ) {
                passwordError.append("Password needs to be at least 8 characters long."); 
               
            } 
        
        if ( password.contains(firstName) || password.contains(lastName) ) {
                passwordError.append("\n" + "Password cannot contain your name.");
                
        } 
        
        if ( password.contains("1") || password.contains("2") || password.contains("3") || password.contains("4")
                                    || password.contains("5") || password.contains("6") || password.contains("7")
                                                || password.contains("8") || password.contains("9") ) {}
        else { passwordError.append("\n" + "Password must contain at least one number."); }
            
        return passwordError;
    }
}
