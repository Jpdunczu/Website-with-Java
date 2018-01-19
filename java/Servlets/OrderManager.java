/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Cart;
import Beans.Customer;
import Beans.Order;
import JDBC.CartDB;
import JDBC.OrderDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
public class OrderManager extends HttpServlet {

    private String ccType;
    private String cc;
    private String custCC;
    private String exp;
    private String crv;
    
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
        String sessionID = session.getId();
        
        
        String url = "/WEB-INF/view/checkout.jsp";
        String action = request.getParameter("action");
        String username = (String) session.getAttribute("username");
        
        if( action.equals("checkout") || action.equals("back") ) {
            
            if ( CartDB.hasCart(username) ) {
                url = "/WEB-INF/view/checkout.jsp";
                Cart cart = CartDB.getCartsRetail(username);
                double subtotal = cart.getCartRetail();
                session.setAttribute("subtotal", subtotal);
                double salesTax = ((subtotal/10)*100)/100;
                
                session.setAttribute("salesTax", salesTax);
                List<Cart> carts = CartDB.getCarts( username );
                session.setAttribute("cartItems",carts);
            } else {
                boolean cartItem = false;
                url = "/WEB-INF/view/cart.jsp";
            }
            
        }else {
            
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zipCode = request.getParameter("zipCode");
            String custAddress = address + ", " + city + ", " + state + " " + zipCode;
            
            request.setAttribute("custAddress", custAddress);    
            request.setAttribute("address", address);
            request.setAttribute("city", city);
            request.setAttribute("state", state);
            request.setAttribute("zipCode", zipCode);
            
            String shipping = request.getParameter("shippingOption");

            String unableToProcess = "We were unable to process your order." + 
                    "\nPlease fix any error's listed below and click the purchase button again.";
            String error = " *** ERROR *** ";
            String missingInfo = "Please correct the missing information.";
            boolean infoIncomplete = false;  

                if( name == null || name.equals("") ){ 
                    request.setAttribute("errorMessageName", error);
                    infoIncomplete = true; 
                } else {
                    request.setAttribute("name", name);
                } 
                
                if ( email == null || email.equals("") ){ 
                    request.setAttribute("errorMessageEmail", error);
                    infoIncomplete = true;
                } else {
                    request.setAttribute("email", email);
                }
                
                if( phone == null || phone.equals("") ){ 
                    request.setAttribute("errorMessagePhone", error);
                    infoIncomplete = true;
                } else {
                    request.setAttribute("phone", phone);
                } 
                
                if( address == null || address.equals("") || city == null || city.equals("") || 
                        state == null || state.equals("") || zipCode == null || zipCode.equals("") ){
                    request.setAttribute("errorMessageAddress", error);
                    infoIncomplete = true;
                } 
                
                if( shipping == null || shipping.equals("") ) {
                    request.setAttribute("errorShippingOption", error);
                    infoIncomplete = true;
                } 
            
            if( ( action.equals("continue") && infoIncomplete == false ) ) {
                
                url = "/WEB-INF/view/orderConfirmation.jsp";
                double shipCost = 0.00;
                String shippingCost = request.getParameter("shippingOption");
                
                
                if( shippingCost.equals("0.00")){
                    shipCost = 0.00;
                }else if ( shippingCost.equals("5.99")){
                    shipCost = 5.99;
                } else if ( shippingCost.equals("12.99")){
                    shipCost = 12.99;
                } else if ( shippingCost.equals("19.99")){
                    shipCost = 19.99;
                }
                
                session.setAttribute("shippingCost", shipCost);
                session.setAttribute("shippingOption", shippingCost);
                double sub = (double) session.getAttribute("subtotal");
                double tax = (double) session.getAttribute("salesTax");
                
                double total = sub + tax + shipCost;
                session.setAttribute("total", total);
                Customer customer = new Customer(name,email,phone,custAddress,username);
                session.setAttribute("customer", customer);
                int check = OrderDB.insertCustomer(customer);
                if( check == 0 ){
                    System.out.println("Error in OrderManager during insertCustomer");
                }
            }
            
                if( action.equals("confirm") ){
                    url = "/WEB-INF/view/orderSuccess.jsp";
                    
                    cc = request.getParameter("ccNumber");
                    ccType = request.getParameter("ccType");
                    
                    exp = request.getParameter("exp");
                    crv = request.getParameter("crv");
                    
                    if( ccType.equals("visa") && !cc.startsWith("4") 
                            || ((ccType.equals("visa") || ccType.equals("mastercard")) && cc.length() != 16) 
                                || ccType.equals("mastercard") && !cc.startsWith("5")
                                    || ccType.equals("amex") && !cc.startsWith("3")
                                        || ccType.equals("amex") && cc.length() != 15 ){
                        request.setAttribute("errorMessageCC", error + " CC information is incorrect for the type specified.");
                        url = "/WEB-INF/view/orderConfirmation.jsp";
                    } else if( ccType == null || ccType.equals("") || cc == null || cc.equals("") || 
                        exp == null || exp.equals("") || crv == null || crv.equals("") ) {
                        request.setAttribute("errorMessageCC", error);
                        url = "/WEB-INF/view/orderConfirmation.jsp";
                    }else{
                        custCC = ccType + " | " + cc + " | " + exp + " | " + crv;
                        GregorianCalendar calendar = new GregorianCalendar();
                        String currentDate = String.valueOf(calendar.get(Calendar.MONTH)) + " "
                        + String.valueOf(calendar.get(Calendar.DATE)) + " "
                        + String.valueOf(calendar.get(Calendar.YEAR));
                        double total = (double) session.getAttribute("total");
                        request.setAttribute("totalCost",total);
                        String shipping1 = (String) session.getAttribute("shippingOption");
                        String status = "Processing";
                        Order order = new Order(custCC,shipping1,username,sessionID,currentDate,total,status,custAddress);
                        if( OrderDB.insertOrder( order ) ) {
                            int orderNumber = OrderDB.getOrderNumber(sessionID);
                            Customer customer = OrderDB.updateCustomer(username,orderNumber);
                            request.setAttribute("customer", customer);   
                        }else {
                            url = "/WEB-INF/view/purchaseError.jsp";  
                        }
                    }
                }
            
            if( action.equals("complete") ) {
                url = "/WEB-INF/view/homePage.jsp";
                Customer customer = OrderDB.getCustomer(username);
                request.setAttribute("customer", customer);
                // get the order number assigned to this successful order
                Order order = OrderDB.getOrder( customer.getOrderNumber() );
                // change the name of each item in the purchased cart to the orderNumber for future cataloging.
                OrderDB.getCartID( order );
                List<Order> orders = new ArrayList<>();
                orders = OrderDB.getOrderHistory(username);
                request.setAttribute("orders", orders);
                int cartCount = 0;
                session.setAttribute("cartCount", cartCount);
            }

            if( infoIncomplete == true ){
                request.setAttribute("unableToProcess", unableToProcess);
                request.setAttribute("missingInfo", missingInfo);
            }

            request.setAttribute("url", url);
        }
        
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
