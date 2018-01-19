<%-- 
    Document   : processOrder
    Created on : Aug 17, 2016, 5:45:00 PM
    Author     : joshuaduncan
--%>
<title>Process Order</title>

<form action="AdminServlet" method="post">
    <div class="container-fluid">
        <div class="text-center text-primary"><h3><strong>Order Processing</strong></h3></div>
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="form-inline">
                    <div class="form-group">
                        <h3 class="form-control-static">Order:
                        <strong class="text-primary"><c:out value="${order.getOrderNumber()}"/></strong></h3>
                    </div>
                   
                    <div class="form-group">
                        <h3 class="form-control-static">Status: <strong class="text-primary"><c:out value="${order.getOrderStatus()}"/></strong></h3>
                    </div>
                </div>   
                        <button type="submit" class="btn btn-danger" name="action" value="backToOrders">Back to Orders</button>
                   
            </div>
            <div class="panel-body">
                <div class="container-fluid">
                    <div class="col-sm-5">
                    <h4><strong><div class="text-primary">Customer Info:</div></strong></h4>
                    <div class="form-group">
                        <label>Name: </label>
                        <c:out value="${order.customer.getName()}"/>
                    </div>
                    <div class="form-group">
                        <label>Billing: </label>
                        <c:out value="${order.customer.getBillingAddress()}"/>
                    </div>
                    <h4><strong><div class="text-primary">Contact:</div> </strong></h4>
                    <div class="form-group">
                        <label>Phone: </label>
                        <c:out value="${order.customer.getPhone()}"/>
                    </div>
                    <div class="form-group">
                        <label>E-mail: </label>
                        <c:out value="${order.customer.getEmail()}"/>
                    </div>
                    </div>
                    <div class="col-sm-2"></div>
                    <div class="col-sm-5">
                        <h4><strong><div class="text-primary">Shipping Info:</div></strong></h4>
                    <div class="form-group">
                        <label>Name: </label>
                        <c:out value="${order.getShippingName()}"/>
                    </div>
                    <div class="form-group">
                        <label>Address: </label>
                        <c:out value="${order.getShippingAddress()}"/>
                    </div>
                    <h4><div class="text-primary">Special Instructions:</div></h4>
                    <div class="well">
                        
                    </div>
                    </div>
                </div>
                    <div class="container-fluid">
                        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Qty</th>
                        <th>Size</th>
                        <th>Product</th>
                        <th>Product Code</th>
                        
                        <th>Cost</th>
                        <th>Retail</th>
                        
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cartItems" items="${cartItems}"> 
                    
                    <tr>
                        <td>
                            <select class="form-control" name="qty">
                                <option><c:out value='${cartItems.getProductCount()}'/></option>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </td>
                        <td>
                            <select class="form-control" name="size">
                                    <option> <c:out value="${cartItems.getSize()}"/> </option>
                                    <option> XS </option>
                                    <option> S </option>
                                    <option> M </option>
                                    <option> L </option>
                                    <option> XL </option>
                            </select>
                        </td>
                        <td><c:out value="${cartItems.product.productName}"/></td>
                        
                        <td><input type="hidden" name="productID" value="<c:out value='${cartItems.product.productID}'/>"><c:out value="${cartItems.product.productID}"/></td>
                        
                        <td><c:out value="${cartItems.product.getProductCost()}"/></td>
                        <td><c:out value="${cartItems.product.getProductRetail()}"/></td>
                        
                        <td>
                            
                                <div class="form-inline">
                                    <button type="submit" class="btn btn-danger" name="action" value="remove">Remove Item</button>  
                                
                                    <button type="submit" class="btn btn-success" name="action" value="update">Update Item</button>
                                </div>
                            
                        </td>        
                    </tr>
                  
                    </c:forEach>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td>
                                <div class="alert alert-success">
                                    <div class="container-fluid">
                                        Shipping: <strong>$ <c:out value="${order.getShippingMethod()}"/></strong>
                                    </div>
                                    <div class="container-fluid">
                                        Subtotal:  <strong>$ <c:out value="${subtotal}"/></strong>
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <div class="text-info"><strong>Status:</strong></div>
                                        <select class="form-control" name="orderStatus">
                                            <option> <c:out value="${order.getOrderStatus()}"/> </option>
                                            <option> Processing </option>
                                            <option> Completed </option>
                                            <option> Canceled </option>
                                            <option> Shipping </option>
                                        </select>
                                    </div>
                                    <div class="form-inline">
                                        <input type="hidden" name="orderNumber" value="<c:out value='${order.getOrderNumber()}'/>">
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-info" name="action" value="completeOrder">Update Order</button>
                                        </div>
                                        <div class="form-group">
                                            <button type="submit" class="btn btn-danger" name="action" value="cancel">Cancel Order</button>
                                        </div>
                                    </div>
                            </td>
                        </tr>
                </tbody>
            </table>
        </div>
                    </div>
            </div>
        </div>
       
    </div>
</form>
