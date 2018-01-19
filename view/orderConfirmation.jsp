<%-- 
    Document   : purchase
    Created on : Aug 1, 2016, 4:56:10 PM
    Author     : joshuaduncan
--%>

<title>Order Confirmation</title>

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
                <div class="progress-bar" role="progressbar" aria-valuenow="75" 
                    aria-valuemin="0" aria-valuemax="100" style="width: 75%;">
                    75% Complete
                </div>    
            </div>
        <div class="col-sm-2"></div>
    </div>


<div class="container-fluid">
    
        <h2 class="text-primary text-center">Confirmation</h2>
            <p class="text-info text-center">Please verify that the following information is accurate. 
                Click the back button to change any information. 
                Click submit to proceed with payment.</p>
        <p class="text-danger text-center">Do not click submit more than once in order to avoid multiple charges.</p>
    
</div>


<div class="container-fluid">
    <div class="col-sm-1"></div>
        <div class="panel panel-default col-sm-5">
            <div class="panel-heading text-center"><h3>Payment Information</h3></div>
            <div class="panel-body">
                
                    <div class="form-group">
                        <label for="custname"><strong>Full Name: </strong></label><p id="custname"> <c:out value="${customer.getName()}"/><p>
                    </div>
                    <div class="form-group">
                        <label for="email"><strong>E-mail: </strong></label> <p id="email"><c:out value="${customer.getEmail()}"/></p>
                    </div>
                    <div class="form-group">
                        <label for="phone"><strong>Phone: </strong></label> <p id="phone"><c:out value="${customer.getPhone()}"/></p>
                    </div>
                    <div class="form-group">
                        <label for="add"><strong>Address: </strong></label> <p id="add"><c:out value="${customer.getBillingAddress()}"/></p>
                    </div>
            </div>
                            <div class="panel-footer ">
                            </div>
        </div>
                        <c:if test="${ shippingDifferent == true }">
                            <div class="panel panel-default col-sm-6">
                                <div class="panel-heading text-center"><h3>Shipping Information</h3></div>
                                <div class="panel-body">
                                    
                                        <div class="form-group">
                                            <strong>Full Name: </strong><div class="text-right"> <c:out value="${shippingName}"/></div>
                                        </div>
                                        <div class="form-group">
                                            <strong>E-mail: </strong> <c:out value="${shippingEmail}"/>
                                        </div>
                                        <div class="form-group">
                                            <strong>Phone: </strong> <c:out value="${shippingPhone}"/>
                                        </div>
                                        <div class="form-group">
                                            <strong>Address: </strong> <c:out value="${shippingAddress}"/>
                                        </div>
                                </div>
                            </div>
                        </c:if>
                    <div class="col-sm-1"></div>
                    <div class="panel panel-default col-sm-4">
                        <div class="panel-heading text-center"><h3>Order Information:</h3></div>
                        <div class="panel-body">
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
                        </div>
                        <div class="panel-footer">
                            <div class="text-right"><strong>Shipping: $</strong> <c:out value="${shippingCost}"/></div>
                            <div class="text-right"><strong>Total: $</strong> <c:out value="${total}"/></div>
                        </div>
                    </div>            
    <div class="col-sm-1"></div>
</div>
                        
<form action="OrderManager" method="post">
                        <div class="container-fluid">
                            <div class="col-sm-3"></div>
                            <div class="panel panel-default col-sm-6">
                                <div class="panel-heading">
                                    <button type="submit" class="btn btn-danger btn-block" name="action" value="back"><span class="glyphicon glyphicon-remove"></span> Back</button>
                                </div>
                                <div class="panel-body">
                                    
                                        <label class="radio-inline"><input type="radio" name="ccType" value="visa"> Visa</label>
                                        <label class="radio-inline"><input type="radio" name="ccType" value="mastercard"> MC</label>
                                        <label class="radio-inline"><input type="radio" name="ccType" value="amex"> Amex</label><br>
                                        <p class="text-danger"><c:out value="${errorMessageCC}"/></p>

                                        <div class="col-xs-6">
                                            <label for="CC">CC: </label>
                                            <input type="text" class="form-control" id="CC" name="ccNumber" placeholder="1234-5678-1011-1213">        
                                        </div>
                                        <div class="col-xs-4">
                                            <label for="expDate">EXP: </label>
                                            <input type="text" class="form-control" id="expDate" name="exp" placeholder="Expiration Date">
                                        </div>
                                        <div class="col-xs-2">
                                            <label for="crv">CRV: </label>
                                            <input type="text" class="form-control" id="crv" name="crv" placeholder="123">
                                        </div>
                                    
                                </div>
                                <div class="panel-footer">
                                    <button type="submit" class="btn btn-success btn-block" name="action" value="confirm"><span class="glyphicon glyphicon-ok"></span> Confirm</button>
                                </div>
                            </div>
                            <div class="col-sm-3"></div>
                        </div>
</form>           