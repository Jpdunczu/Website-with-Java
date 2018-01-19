<%-- 
    Document   : orders
    Created on : Aug 15, 2016, 11:06:19 PM
    Author     : joshuaduncan
--%>
<title>Order List</title>

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
                                <th><strong>Date:</strong></th>
                                <th><strong>Order#:</strong></th>
                                <th><strong>Status:</strong></th>
                                <th><strong>Shipping:</strong></th>
                                <th><strong>Total:</strong></th>
                                
                            </tr>
                        </thead>
                            
                        <tbody>
                            <c:forEach var="orders" items="${orders}">
                                <tr>

                                    <td><c:out value="${orders.getOrderDate()}"/></td>
                                    <td><input type="hidden" name="orderNumber" value="<c:out value='${orders.getOrderNumber()}'/>"><c:out value="${orders.getOrderNumber()}"/></td>
                                    <td><c:out value="${orders.getOrderStatus()}"/></td>
                                    <td><c:out value="${orders.getShippingMethod()}"/></td>
                                    <td><c:out value="${orders.getOrderTotal()}"/></td>
                                    <td><button type="submit" class="btn btn-default " name="action" value="processOrder">Process Order</button></td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div> 
            </div>
        </div>
    </div>
<div class="col-sm-1"></div>
</div>

</form>