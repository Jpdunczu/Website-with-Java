<%-- 
    Document   : orderConfirmation
    Created on : Aug 1, 2016, 6:24:24 PM
    Author     : joshuaduncan
--%>

<title>Order Success</title>

<div class="container-fluid">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="navbar-header">
                <div class="navbar-brand">
                    <button class="btn-link" data-toggle="modal" href="#myModal">Store Name</button>
                </div>
            </div>
<!-- NAVIGATION BAR -->
        </nav>
</div>


    <div class="container-fluid">
        <div class="col-sm-2"></div>
            <div class="progress col-sm-8">
                <div class="progress-bar" role="progressbar" aria-valuenow="100" 
                    aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
                    100% Complete
                </div>    
            </div>
        <div class="col-sm-2"></div>
    </div>

<div class="container-fluid">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <h1 class="text-center">Thank you!</h1>
    </div>
    <div class="col-sm-2"></div>
</div>

<div class="container-fluid">
    <div class="alert alert-success text-center">
        <strong>Order Success!</strong> Your payment has been processed and your order has been placed!</strong>
    </div>
</div>

<div class="container-fluid">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading text-center"><h3>Order Information: </h3></div>
            <div class="panel-body">
                <div class="text-primary>"<h4><strong>Order Number: </strong></h4> <mark><c:out value="${customer.getOrderNumber()}"/></mark></div>
                <div class="text-info"><strong>Thank you for shopping with us! </strong>an e-mail containing your receipt and order information has been sent.</div>
                <br>
                <div class=""
                    <div class="text-primary">
                        <strong>Billing Address</strong>
                    </div>
                    <div class="form-group">
                        <strong>Full Name: </strong> <c:out value="${customer.getName()}"/>
                    </div>
                    <div class="form-group">
                        <strong>Phone: </strong> <c:out value="${customer.getPhone()}"/>
                    </div>
                    <div class="form-group">
                        <strong>Address: </strong> <c:out value="${customer.getBillingAddress()}"/>
                    </div>
                
                    
                        <div class="text-primary">
                        <strong>Shipping Address</strong>
                    </div>
                    <div class="form-group">
                        <strong>Full Name: </strong> <c:out value="${customer.getShippingName()}"/>
                    </div>
                    <div class="form-group">
                        <strong>Phone: </strong> <c:out value="${customer.getShippingPhone()}"/>
                    </div>
                    <div class="form-group">
                        <strong>Address: </strong> <c:out value="${customer.getShippingAddress()}"/>
                    </div>
                
                <div class="table-responsive">
                    <table class="table">
                        <c:forEach var="cartItems" items="${cartItems}">
                            <tbody>
                                <tr>
                                    <td><strong><c:out value="${cartItems.productCount}"/>x </strong></td>
                                    <td><c:out value="${cartItems.size}"/></td>
                                    <td><c:out value="${cartItems.product.productName}"/></td>
                                    <td><c:out value="${cartItems.product.productID}"/></td>
                                    <td><strong>$ </strong><c:out value="${cartItems.getCartCost()}"/></td>
                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
                <ul class="list-inline">
                    <li><div class="text-primary"><strong>Shipping: </strong> <c:out value="${shippingCost}"/></div></li>
                    <li><div class="text-primary"><strong>Total: </strong> <c:out value="${totalCost}"/></div></li>
                </ul>
            </div>
                <form action="OrderManager" method="post">
                    <div class="panel-footer">
                        <button type="submit" class="btn btn-primary btn-block" name="action" value="complete">
                        <span class="glyphicon glyphicon-home"></span> Home Page</button>
                    </div>
                </form>
        </div>
    </div>
    <div class="col-sm-2"></div>
</div>
