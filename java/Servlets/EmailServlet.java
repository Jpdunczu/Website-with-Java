/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.User;
import Email.MailUtilGmail;
import JDBC.UserDB;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joshuaduncan
 */
public class EmailServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        String url = "/registration.jsp";
        if(action.equals("join")){
            
            String email = request.getParameter( "email" );
            if(UserDB.emailExists(email) == false){
                url = "/thanks.jsp";
            String to = email;
            UserDB.updateMailing(email);
            
            String from = "DO_NOT_REPLY";
            String subject = "Welcome to our email list";
            String body = "Welcome!\n\n"
                    + "Thanks for joining our email list. "
                    + "We'll make sure to send "
                    + "you announcements about new products "
                    + "and promotions.\n"
                    + "Have a great day and thanks again!\n\n"
                    + "Tsering Dhondup\n"
                    + "Zeyma Collections";
            
            boolean isBodyHtml = false;
            
            try {
                MailUtilGmail.sendMail( to, from, subject, body, isBodyHtml );
            } catch ( MessagingException e ) {
                String errorMessage
                        = "ERROR: Unable to send email. "
                        + "Check Tomcat logs for details.<br>"
                        + "ERROR MESSAGE: " + e.getMessage();
                request.setAttribute( "errorMessage", errorMessage );
                this.log(
                "Unable to send email. \n"
                + "=====================\n"
                + "TO: " + email + "\n"
                + "FROM: " + from + "\n"
                + "SUBJECT: " + subject + "\n\n"
                + body + "\n\n" );
          
            }
          }
           
        }
        
        
     
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    
    }
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
