/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Customer;
import Beans.Order;
import Beans.Product;
import JDBC.OrderDB;
import JDBC.ProductDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author joshuaduncan
 */
public class indexNavigation extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        String url = "/index.jsp";
        boolean login;
        boolean noSales;
        try{
            login = (boolean)session.getAttribute("login");
            session.setAttribute("login", login);
        } catch (Exception e){
            login = false;
            session.setAttribute("login",login);
        }
        
        if( action.equals("home")){
            if( login == true ){
                url = "/WEB-INF/view/homePage.jsp";
                String username = (String)session.getAttribute("username");
                if( OrderDB.hasOrderHistory(username) ) {
                    Customer customer = OrderDB.getCustomer(username);
                    // get the order number assigned to this successful order
                    Order order = OrderDB.getOrder( customer.getOrderNumber() );
                    List<Order> orders = OrderDB.getOrderHistory(username);
                    request.setAttribute("orders", orders);
                    request.setAttribute("customer", customer);
                }
            }
        }
        
        
        if( action.equals( "gifts" ) ) {
            url = "/WEB-INF/view/gifts.jsp";
            List<Product> products = ProductDB.getProducts("gift");
            request.setAttribute("products", products);
            request.setAttribute("url", url);
        } 
        else if ( action.equals( "sales" ) ) {
            url = "/WEB-INF/view/sales.jsp";
            List<Product> products = ProductDB.getSales("Y");
            if( products.isEmpty() ){
                noSales = true;
                request.setAttribute("noSales", noSales);
            } else { 
                noSales = false;
                request.setAttribute("products", products); 
                request.setAttribute("noSales", noSales);
            }
            
            request.setAttribute("url", url);
        } 
        else if ( action.equals( "top" ) ) {
            url = "/WEB-INF/view/topSellers.jsp";
            List<Product> products = ProductDB.getProducts("top");
            request.setAttribute("products", products);
            request.setAttribute("url", url);
        } 
        else if ( action.equals( "featured" ) ) {
            url = "/WEB-INF/view/featuredItems.jsp";
            List<Product> products = ProductDB.getProducts("feat");
            request.setAttribute("products", products);
            request.setAttribute("url", url);
        }
        
        if( action.equals("requestNewPW") ) {
            url = "/WEB-INF/view/passwordRequest.jsp";
            String pwRequestUser = request.getParameter("pwRequestUser");
        }
        
        if( action.equals("logout")){
            session = request.getSession();
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
        else{
            getServletContext()
                 .getRequestDispatcher(url)
                       .forward(request,response);
        }
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
