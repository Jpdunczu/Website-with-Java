/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author joshuaduncan
 */
public class User implements Serializable{
    
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private final int admin = 0;
    private String salt;
    int emailList;
    String customer;
    
    public User(){}
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String salt){
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public User(String username, String firstName, String lastName, String email, int emailList, String customer) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.emailList = emailList;
        this.customer = customer;
    }

    public User(String firstName, String lastName, String email, String username, String password) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public User(String username, String password, String firstName, String lastName, String email, String salt) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = salt;
    }

    public User(String username, String password, String firstName, String lastName, String email, String salt, int emailList, String customer) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salt = salt;
        this.emailList = emailList;
        this.customer = customer;
    }

    public String getSalt() {
        return salt;
    }

    public int getEmailList() {
        return emailList;
    }

    public void setEmailList(int emailList) {
        this.emailList = emailList;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    

    public int getAdmin() {
        return admin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + '}';
    }

    
 
}
