/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Cart;
import Beans.Customer;
import Beans.Order;
import Beans.User;
import JDBC.Authenticate;
import JDBC.CartDB;
import JDBC.OrderDB;
import JDBC.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
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
public class Authenticator extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        session.removeAttribute("password");
        String action = request.getParameter("action");
        boolean isChecked = false;
        /* ----------------------------------------- */
        /* ----------------------------------------- */
        
        
        /* ----------------------------------------- */
        /* ------------ CHECK PARAMETERS ----------- */
        /* ----------------------------------------- */
        
        String url= "/WEB-INF/view/loginError.jsp";
        Boolean login = false;
        String message = "";
        
        if( username.equals("WangchenDechen") ) {
            
            session.removeAttribute("username");
            //response.sendRedirect("https://localhost:8443/StarDust/admin/login.jsp");
            //response.sendRedirect("admin/login.jsp"); 
            url = "/admin/login.jsp";
        } 
        
       
        if( action.equals("login") ) {
            
            User user = new User(username, password);    
            
            if( Authenticate.authenticator(user) ){
                url = "/WEB-INF/view/homePage.jsp";
                login = true;
                
                    if( UserDB.isAdmin( username ) ) {
                        
                        url = "/admin/index.jsp"; 
            
                    }
                    
                
                User user2 = UserDB.selectUser(username);
                String firstName = user2.getFirstName();
                session.setAttribute("firstName", firstName);
                
                
                if( OrderDB.hasOrderHistory(username) ) {
                    url = "/WEB-INF/view/homePage.jsp";
                    Customer customer = OrderDB.getCustomer(username);
                    List<Order> orders = OrderDB.getOrderHistory(username);
                    request.setAttribute("orders", orders);
                    request.setAttribute("customer", customer);
                }
                
                if( CartDB.hasCart(username) ) {
                    int cartCount = CartDB.cartCount(username);
                    session.setAttribute( "cartCount", cartCount );
                }
            }
            
            
            
            
            
            request.setAttribute( "message", message );
            request.setAttribute( "url", url );
            session.setAttribute( "login", login );
  
        }
        session.setAttribute("username", username);
        
        
        
        getServletContext()
            .getRequestDispatcher(url)
            .forward( request, response );
        
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
