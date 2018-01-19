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
import JDBC.CartDB;
import JDBC.OrderDB;
import JDBC.ProductDB;
import java.io.IOException;
import java.io.PrintWriter;
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
public class CartServlet extends HttpServlet {

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
        String sessionId = session.getId();
        String url = "/WEB-INF/view/cart.jsp";
        request.setAttribute("url",url);
        String action = request.getParameter("action");
        String username = (String)session.getAttribute("username");
        int productID = Integer.parseInt(request.getParameter("productID"));
        String quantity = request.getParameter("quantity");
        String size = request.getParameter("size");
        
        boolean cartItem = false;
        
        Product product = ProductDB.getProduct( productID );
        
        if( action.equals( "addToCart" ) ) {
            if( CartDB.cartItemExists(username, productID)){
                boolean hasItem = true;
                request.setAttribute("hasItem", hasItem);
            } else {
                Cart cart = new Cart( username, quantity, productID );
                int check = CartDB.insertCart( cart, sessionId, size, quantity );
                if( check == 0 ) {
                    System.out.println("Insert Cart has failed, check MYSQL");
                }
                cartItem = true;
                boolean newItemAdded = true;
                request.setAttribute("newItemAdded", newItemAdded);
                List<Cart> carts = CartDB.getCarts(username);
                Cart cartRetail = CartDB.getCartsRetail(username);
                request.setAttribute( "cartItems", carts );
                request.setAttribute("subtotal", cartRetail.getCartRetail());
            }
        }
        
        if( action.equals( "update" ) ) {
            String productCount = request.getParameter("qty");
            
            if(size == null){
                size = request.getParameter("size");
            }
            if( Integer.parseInt(productCount) <= 0 ) {
                
                int row = CartDB.selectCart( username, productID );
                if( row != 0 ){
                    CartDB.removeItem( row );
                }
            } else {
                CartDB.updateCart( username, productID, productCount, size );
                List<Cart> carts = CartDB.getCarts(username);
                if( !carts.isEmpty() ){ cartItem = true; } else { cartItem = false; } 
                Cart cartRetail = CartDB.getCartsRetail(username);
                request.setAttribute("cartItems", carts);
                request.setAttribute("subtotal", cartRetail.getCartRetail());
            }
        }
              
        if( action.equals( "remove" ) ) {
            
            int row = CartDB.selectCart( username, productID );
            if( row != 0 ){
                CartDB.removeItem(row);
            }
            List<Cart> carts = CartDB.getCarts( username );
            Cart cartRetail = CartDB.getCartsRetail(username);
            if( !carts.isEmpty() ){
                cartItem = true;
                
            } else { cartItem = false; } 
            
            request.setAttribute( "cartItems", carts );
            request.setAttribute("subtotal", cartRetail.getCartRetail());
        }
        
        if( action.equals( "viewCart" ) ) {
            List<Cart> carts = CartDB.getCarts( username );
            Cart cartRetail = CartDB.getCartsRetail(username);
            if( carts.isEmpty() ){
                cartItem = false;
            } else { cartItem = true; }
            request.setAttribute( "cartItems", carts );
            request.setAttribute("subtotal", cartRetail.getCartRetail());
        }
        
        int cartCount = CartDB.cartCount(username);
        session.setAttribute( "cartCount", cartCount );
        session.setAttribute("cartItem",cartItem);
        
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
