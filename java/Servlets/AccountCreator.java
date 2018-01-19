/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.User;
import JDBC.UserDB;
import Security.PasswordCheck;
import Security.PasswordUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joshuaduncan
 */
public class AccountCreator extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /* ----------------------------------------- */
        /* -------------- GET SESSION -------------- */
        /* ----------------------------------------- */
        HttpSession session = request.getSession();
        String userId = session.getId();
        Long timeAccessed = session.getLastAccessedTime();
        Long dateCreated = session.getCreationTime();
        session.setMaxInactiveInterval(-1);
        /* ----------------------------------------- */
        /* ----------------------------------------- */
        
        
        
        /* ----------------------------------------- */
        /* --------------- COOKIES ----------------- */
        /* ----------------------------------------- */
        Cookie c = new Cookie("userIdCookie", userId);
        c.setMaxAge(-1);
        c.setPath("/");
        /* ----------------------------------------- */
        /* ----------------------------------------- */
        
        
        
        /* ----------------------------------------- */
        /* ------------ GET PARAMETERS ------------- */
        /* ----------------------------------------- */
        
        String action = request.getParameter("action");
        
        String username = request.getParameter("username");
        
        String password = request.getParameter("password");
        session.removeAttribute("password");
        String firstName = request.getParameter("firstName");
        
        String lastName = request.getParameter("lastName");
        
        String email = request.getParameter("email");
        
        
        String address1 = request.getParameter("address1");
        if( address1 == null ){ address1 = ""; }
        String address2 = request.getParameter("address2");
        if( address2 == null ){ address2 = ""; }
        String city = request.getParameter("city");
        if( city == null ){ city = ""; }
        String state = request.getParameter("state");
        if( state == null ){ state = ""; }
        String zipCode = request.getParameter("zipCode");
        if( zipCode == null ){ zipCode = ""; }
        /* ----------------------------------------- */
        /* ----------------------------------------- */
        
        String url = "/registrationPage.jsp";
        
        StringBuilder passwordError = new StringBuilder();
        boolean pwError = true;
        
        if( action.equals("create") ){
            session.setAttribute( "timeAccessed", timeAccessed );
            session.setAttribute( "dateCreated", dateCreated );
          
            // Check password Strength
            passwordError = ( PasswordCheck.PWCheck(password,firstName,lastName) );
            if( passwordError.length() == 0 ) {
                pwError = false;
            }else {
                session.setAttribute("passwordError", passwordError);
            }
            
            session.setAttribute("pwError", pwError);
            
            if( pwError == false ){
                if( UserDB.emailExists(email) ) {
                    String userExists = "Error: there is already an account with this e-mail address.";
                    boolean exists = true;
                    url = "/registrationPage.jsp";
                    request.setAttribute("userExists", userExists);
                    request.setAttribute("exists", exists);
                    
                }else if ( UserDB.usernameExists(username)){
                    String userExists = "Error: that username is not available.";
                    boolean exists = true;
                    url = "/registrationPage.jsp";
                    request.setAttribute("userExists", userExists);
                    request.setAttribute("exists", exists);
                    
                }else {
                    try {
                        String hashedPassword = PasswordUtil.hashPassword(password);
                        String salt = PasswordUtil.getSalt();
                        String hashedAndSaltedPassword = hashedPassword + salt;
                        User user = new User(username, hashedAndSaltedPassword, firstName, lastName, email, salt);
                        int insert = UserDB.insert(user);
                        if( insert == 0 ){
                            System.out.println("Error inserting new user in Authenticator.");
                        }
                        url = "/WEB-INF/view/thanks.jsp";
                        String message = " Your account was successfully created!";
                        boolean create = true;
                        request.setAttribute( "create", create );
                        request.setAttribute( "accountCreated", message );
                    }catch ( NullPointerException e) {
                        System.out.println(e);
                    }
                    
                    if( request.getParameter( "emailList" ) != null ){
                        boolean emailListJoined = true;
                        String thanksForJoining = "Thank you for signing up for our email list!" 
                                + "We will send you our monthly sales mails.";
                        request.setAttribute("emailListJoined", emailListJoined);
                        request.setAttribute("thanksForJoining", thanksForJoining);
                    }
                } 
            }
        }
        
        /* ----------------------------------------- */
        /* ------------ SET PARAMETERS ------------- */
        /* ----------------------------------------- */
        
        request.getSession().setAttribute("username",username);
        request.getSession().setAttribute("firstName", firstName);
        request.getSession().setAttribute("lastName", lastName);
        request.getSession().setAttribute("email", email);
        
        request.setAttribute("url", url);
        /* ----------------------------------------- */
        /* ----------------------------------------- */
        
        getServletContext()
            .getRequestDispatcher(url)
                .forward(request,response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
