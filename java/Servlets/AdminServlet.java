/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Cart;
import Beans.Customer;
import Beans.Order;
import Beans.Product;
import Beans.User;
import JDBC.CartDB;
import JDBC.OrderDB;
import JDBC.ProductDB;
import JDBC.UserDB;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author joshuaduncan
 */

public class AdminServlet extends HttpServlet {
    

    
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
        
        List<Order> orders = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        
        HttpSession session = request.getSession();
        String url = "/admin/index.jsp";
        String action = request.getParameter("action");
        
        if( action.equals("addItem")){
            url = "/admin/addItems.jsp";
            List<Product> products = ProductDB.getAllProducts();
            request.setAttribute("products",products);
        }
        
        if( action.equals("users")){
            url = "/admin/users.jsp";
            users = UserDB.getAdminUsers();
            request.setAttribute("users",users);
        }
        
        if( action.equals("orders") || action.equals("backToOrders")){
            url = "/admin/orders.jsp";
            orders = OrderDB.getOrders();
            request.setAttribute("orders",orders);
        }
        
        if(action.equals("customers")){
            url = "/admin/customers.jsp";
            customers = OrderDB.getCustomers();
            request.setAttribute("customers",customers);
        }
        
        if( action.equals("processOrder") ){
            
            url = "/admin/processOrder.jsp";
            System.out.println("orderNum pre-parse: " + request.getParameter("number"));
            System.out.println("orderNum pre-parse: " + request.getParameter("orderNumber"));
            
            int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
            System.out.println("AdminServlet >> orders >> processOrder >> orderNumber: " + orderNumber);
            Order order = OrderDB.getFullOrder(orderNumber);
            List<Cart> cartItems = CartDB.getCarts(String.valueOf(orderNumber));
            Customer customer = OrderDB.getFullCustomer(order.getCartName());
            order.setCustomer(customer);
            request.setAttribute("order",order);
            request.setAttribute("cartItems",cartItems);
            Cart cartRetail = CartDB.getCartsRetail(String.valueOf(orderNumber));
            request.setAttribute("subtotal", cartRetail.getCartRetail());    
        }
        
        if(action.equals("update") || action.equals("remove")){
            
            if( action.equals("update")){
                System.out.println("here from action==update");
            }
            if( action.equals("remove")){
                System.out.println("here from action==remove");
            }
            
            int productID = Integer.parseInt(request.getParameter("productID"));
            String size = request.getParameter("size");
            String username = request.getParameter("orderNumber");
            
            if(action.equals("update")) {
                String productCount = request.getParameter("qty");
                System.out.println("here from action==update and producCount = " + productCount);
                if( Integer.parseInt(productCount) <= 0 ) {
                    
                    int row = CartDB.selectCart(username,productID);
                    if(row != 0){
                        CartDB.removeItem(row);
                    }
                } else {
                    CartDB.updateCart(username,productID,productCount,size);
                    System.out.println("Here from action==update && productCount > 0");
                }
            }
              
            if(action.equals("remove")) {

                int row = CartDB.selectCart( username, productID );
                if( row != 0 ){
                    CartDB.removeItem(row);
                }
            }
            
            url = "/admin/processOrder.jsp";
            int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
            Order order = OrderDB.getFullOrder(orderNumber);
            List<Cart> cartItems = CartDB.getCarts(order.getCartName());
            Customer customer = OrderDB.getFullCustomer(order.getCartName());
            order.setCustomer(customer);
            request.setAttribute("order",order);
            request.setAttribute("cartItems",cartItems);
            Cart cartRetail = CartDB.getCartsRetail(order.getCartName());
            request.setAttribute("subtotal", cartRetail.getCartRetail()); 
            
        }
        
        
        
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
