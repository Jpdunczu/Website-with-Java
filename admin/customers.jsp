<%-- 
    Document   : customers
    Created on : Aug 15, 2016, 11:06:08 PM
    Author     : joshuaduncan
--%>
<title>Customer List</title>

<form action="AdminServlet" method="post">
    <div class="container-fluid">
        <div class="col-sm-1"></div>
            <div class="col-sm-10">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3><div class="text-primary">Admin Console</div></h3>
                        <button type="submit" class="btn btn-danger" name="action" value="backToAdmin">Back to Console</button>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Username</th>
                                        <th>Email</th>
                                        <th>Phone</th>
                                        <th>Billing</th>
                                        <td></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="customers" items="${customers}">
                                    <tr>
                                        <td><c:out value="${customers.getName()}"/></td>
                                        <td><c:out value="${customers.getUsername()}"/></td>
                                        <td><c:out value="${customers.getEmail()}"/></td>
                                        <td><c:out value="${customers.getPhone()}"/></td>
                                        <td><c:out value="${customers.getBillingAddress()}"/></td>
                                        <td><button type="button" class="btn btn-default" data-toggle="modal" data-target="#<c:out value='${customers.getUsername()}'/>">Edit</button></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        <div class="col-sm-1"></div>
    </div>
<div id="<c:out value='${customers.getUsername()}'/>" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h3 class="modal-title">Customer Edit</h3>
            </div>
            <div class="modal-body">
                <div class="text-primary">
                    Customer Info:
                </div>
                                                    
                <div class="well">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" class="form-control" id="name" name="name" value="<c:out value='${customers.getName()}'/>">
                    </div>
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" class="form-control" id="username" name="username" value="<c:out value='${customers.getUsername()}'/>" disabled>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" class="form-control" id="email" name="email" value="<c:out value='${customers.getEmail()}'/>">
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input type="text" class="form-control" id="phone" name="phone" value="<c:out value='${customers.getPhone()}'/>">
                    </div>
                    <div class="form-group">
                        <label for="billingAddress">Billing Address:</label>
                        <input type="text" class="form-control" id="billingAddress" name="billingAddress" value="<c:out value='${customers.getBillingAddress()}'/>">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block" name="action" value="updateCustomer">Update</button>
                    </div>
                </div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><strong>Order#:</strong></th>
                            <th><strong>Status:</strong></th>
                            <th><strong>Shipping:</strong></th>
                            <th><strong>Total:</strong></th>
                        </tr>
                    </thead>
                    <c:forEach var="orders" items="${customers.getOrders()}">
                    <tbody>
                        <tr>
                            <td><input type="hidden" name="orderNumber" value="<c:out value='${orders.getOrderNumber()}'/>"><c:out value="${orders.getOrderNumber()}"/></td>
                            <td><c:out value="${orders.getOrderStatus()}"/></td>
                            <td><c:out value="${orders.getShippingMethod()}"/></td>
                            <td><c:out value="${orders.getOrderTotal()}"/></td>
                            <td><button type="submit" class="btn btn-default " name="action" value="processOrder">Edit Order</button></td>
                        </tr>
                    </tbody>
                    </c:forEach>
                </table>   
            </div>
        </div>
    </div>
</div>
</div>
    </c:forEach>
</form>
