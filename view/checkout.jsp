<%-- 
    Document   : checkout
    Created on : Jul 26, 2016, 7:53:37 PM
    Author     : joshuaduncan
--%>

<title>Checkout</title>

<div class="container-fluid">
    <div class="col-sm-1"></div>
        <nav class="navbar navbar-default navbar-fixed">
            <div class="navbar-header">
                <div class="navbar-brand">
                    <button class="btn-link" data-toggle="modal" href="#myModal">Store Name</button>
                </div>
            </div>
<!-- NAVIGATION BAR -->
<form action="indexNavigation" method="post">
    <ul class="nav navbar-nav">
                    <li><a><button type="submit" class="btn-link" name="action" value="home"><span class="glyphicon glyphicon-home"></span> Home</button></a></li>
                    <li><a><button type="submit" class="btn-link" name="action" value="sales">Sales</button></a></li>
                    <li><a><button type="submit" class="btn-link" name="action" value="featured">Featured Items</button></a></li>
                    <li><a><button type="submit" class="btn-link" name="action" value="top">Top Sellers</button></a></li>
                    <li><a><button type="submit" class="btn-link" name="action" value="gifts">Gifts</button></a></li>
    </ul>
</form>
<form action="CartServlet" method="post">
      <ul class="nav navbar-nav navbar-right">
          <li><a><button type="submit" class="btn-link" name="action" value="viewCart"><span class="glyphicon glyphicon-shopping-cart"></span><span class="badge"><c:if test="${cartCount > 0}"><c:out value="${cartCount}"/></c:if></span> Cart</button></a></li>
      </ul>
</form>
        </nav>
</div>    

<div class="container-fluid">
    <div class="col-sm-2"></div>
        <div class="progress col-sm-8">
            <div class="progress-bar" role="progressbar" aria-valuenow="50" 
                    aria-valuemin="0" aria-valuemax="100" style="width: 50%;">
                    50% Complete
            </div>
        </div>
    <div class="col-sm-2"></div>
</div>

<div class="container-fluid">
    <div class="col-sm-2"></div>
        <div class="row">    
            <h1 class="text-primary text-center">Checkout</h1>
            <p class="text-info text-center"> Please provide the following information to complete the Checkout process.</p>
            <p class="text-danger text-center"><c:out value="${unableToProcess}"/></p>
        </div>
    <div class="col-sm-2"></div>
</div>
        <form action="OrderManager" method="post">       
<div class="container-fluid">
    <div class="col-sm-1"></div>
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading"><a data-toggle="collapse" href="#collapse2"><h4>Billing Information: </h4></a><p class="text-danger"><c:out value="${missingInfo}"/></p>
                </div>
                    <div id="collapse2" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="well">
                                <div class="form-group">
                                    <label for="nme">Name: <small>( Full name as it appears on your Credit Card )</small><p class="text-danger"><c:out value="${errorMessageName}"/></p></label>
                                    <input type="text" class="form-control" id="nme" name="name" value="<c:out value='${name}'/>" placeholder="Name">
                                </div>
                                <div class="form-group">
                                    <label for="emal">E-mail: <p class="text-danger"><c:out value="${errorMessageEmail}"/></p></label>
                                    <input type="email" class="form-control" id="emal" name="email" value="<c:out value='${email}'/>" placeholder="E-mail">
                                </div>
                                <div class="form-group">
                                    <label for="phne">Phone: <p class="text-danger"><c:out value="${errorMessagePhone}"/></p></label>
                                    <input type="tel" class="form-control" id="phne" name="phone" value="<c:out value='${phone}'/>" placeholder="Phone Number">
                                </div>
                            </div>            
                                <div class="form-group">
                                    <label for="add">Address: <p class="text-danger"><c:out value="${errorMessageAddress}"/></p></label>
                                    <input type="text" class="form-control" id="add" name="address" value="<c:out value='${address}'/>" placeholder="Address">
                                        <div class="col-xs-5">
                                            <label>City: </label>
                                            <input type="text" class="form-control" id="city" name="city" value="<c:out value='${city}'/>" placeholder="City">
                                        </div>
                                        <div class="col-xs-2">
                                            <label>State: </label>
                                            <select class="form-control" name="state" id="state" placeholder="">
                                                <option><c:out value="${state}"/></option>
                                                <option>AB</option><option>AE</option><option>AK</option><option>AL</option><option>AR</option><option>AS</option><option>AZ</option>
                                                <option>BC</option><option>CA</option><option>CO</option><option>CT</option><option>DC</option><option>DE</option><option>FL</option>
                                                <option>FM</option><option>GA</option><option>GU</option><option>HI</option><option>IA</option><option>ID</option><option>IL</option>
                                                <option>IN</option><option>KS</option><option>KY</option><option>LA</option><option>MA</option><option>MB</option><option>MD</option>
                                                <option>ME</option><option>MH</option><option>MI</option><option>MN</option><option>MO</option><option>MP</option><option>MS</option>
                                                <option>MT</option><option>NB</option><option>NC</option><option>ND</option><option>NE</option><option>NH</option><option>NJ</option>
                                                <option>NL</option><option>NM</option><option>NS</option><option>NT</option><option>NU</option><option>NV</option><option>NY</option>
                                                <option>OH</option><option>OK</option><option>ON</option><option>OR</option><option>PA</option><option>PE</option><option>PR</option>
                                                <option>PW</option><option>QC</option><option>RI</option><option>SC</option><option>SD</option><option>SK</option><option>TN</option>
                                                <option>TX</option><option>UT</option><option>VA</option><option>VI</option><option>VT</option><option>WA</option><option>WI</option>
                                                <option>WV</option><option>WY</option><option>YT</option>
                                            </select>
                                        </div>
                                        <div class="col-xs-4">
                                            <label>Zip: </label>
                                            <input type="text" class="form-control" id="zip" name="zipCode" value="<c:out value='${zipCode}'/>" placeholder="Zip">
                                        </div>
                                </div>
                        </div>
                    </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4><a data-toggle="collapse" href="#collapse1">Shipping Address <span class="glyphicon glyphicon-chevron-down"></span></a></h4>
                </div>
                    <div id="collapse1" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="form-group">
                                <label for="name">Name: <c:out value="${errorMessageName}"/></label>
                                <input type="text" class="form-control" id="name" name="shippingName" value="<c:out value='${shippingName}'/>" placeholder="Name">
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone: <c:out value="${errorMessagePhone}"/></label>
                                <input type="tel" class="form-control" id="phone" name="shippingPhone" value="<c:out value='${shippingPhone}'/>" placeholder="Phone Number">
                            </div>
                            <div class="form-group">
                                <label for="address">Address: <c:out value="${errorMessageAddress}"/></label>
                                <input type="text" class="form-control" id="address" name="shippingAddress" value="<c:out value='${shippingAddress}'/>" placeholder="Address">
                            </div>    
                            <div class="form-group">
                                <label>City: </label><br>
                                <input type="text" class="form-control" id="city" name="shippingCity" value="<c:out value='${shippingCity}'/>" placeholder="City">
                            </div>
                            <div class="form-group">
                                <label>State: </label><br>
                                    <select class="form-control" name="shippingState" placeholder="">
                                        <option><c:out value="${shippingState}"/></option>
                                        <option>AB</option><option>AE</option><option>AK</option><option>AL</option><option>AR</option><option>AS</option><option>AZ</option>
                                        <option>BC</option><option>CA</option><option>CO</option><option>CT</option><option>DC</option><option>DE</option><option>FL</option>
                                        <option>FM</option><option>GA</option><option>GU</option><option>HI</option><option>IA</option><option>ID</option><option>IL</option>
                                        <option>IN</option><option>KS</option><option>KY</option><option>LA</option><option>MA</option><option>MB</option><option>MD</option>
                                        <option>ME</option><option>MH</option><option>MI</option><option>MN</option><option>MO</option><option>MP</option><option>MS</option>
                                        <option>MT</option><option>NB</option><option>NC</option><option>ND</option><option>NE</option><option>NH</option><option>NJ</option>
                                        <option>NL</option><option>NM</option><option>NS</option><option>NT</option><option>NU</option><option>NV</option><option>NY</option>
                                        <option>OH</option><option>OK</option><option>ON</option><option>OR</option><option>PA</option><option>PE</option><option>PR</option>
                                        <option>PW</option><option>QC</option><option>RI</option><option>SC</option><option>SD</option><option>SK</option><option>TN</option>
                                        <option>TX</option><option>UT</option><option>VA</option><option>VI</option><option>VT</option><option>WA</option><option>WI</option>
                                        <option>WV</option><option>WY</option><option>YT</option>
                                    </select>
                            </div>
                            <div class="form-group">
                                <label>Zip: </label><br>
                                <input type="text" class="form-control" id="zipCode" name="shippingZip" value="<c:out value='${shippingZipCode}'/>" placeholder="zip code">
                            </div>
                        </div>
                    </div>
            </div>
            <div class="well">
                <label for="bttn">&nbsp;</label>
                <input type="hidden" name="action" value="continue"> 
                <button type="submit" class="btn btn-info form-control" id="bttn" name="action" value="continue">Continue</button>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h4>Shipping Options: </h4>
                </div>
                <div class="panel-body">
                    <div class="well">
                        <select class="form-control" id="shippingOption" name="shippingOption" onchange="shippingFunction()">
                            <option>Select Shipping Method</option>
                            <option value=0.00>Standard ( 7 - 10 business days )</option>
                            <option value=5.99>Expedited ( 3 - 5 business days )</option>
                            <option value=12.99>Rush ( 2 business days )</option>
                            <option value=19.99>Overnight ( < 2 business days )</option>
                        </select>
                    </div>
                        <p class="text-danger"><c:out value="${errorShippingOption}"/></p>
                </div>
            </div>
            <div class="well">
                <div class="panel panel-default">
                    <div class="panel-heading text-center">
                        <h4><a data-toggle="collapse" href="#cart">Your Shopping Cart </h4></a>
                    </div>
                    <div id="cart" class="panel-collapse collapse">
                        <div class="panel-body">
                            <div class="well">
                                <table class="table">
                                    <thead>
                                        <th>Qty</th><th>Item</th><th>Price</th>
                                    </thead>
                                        <c:forEach var="cartItems" items="${cartItems}">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <strong><c:out value="${cartItems.productCount}"/>x </strong>
                                            </td>
                                            <td>
                                                <div class="form-group"><a href="<c:out value='${cartItems.product.productName}'/>"><c:out value="${cartItems.product.productName}"/></a></div>
                                            </td>
                                            <td>
                                                <p><strong>$ </strong> <c:out value="${cartItems.product.productRetail}"/></p></div>
                                            </td>
                                        </tr>
                                    </tbody>
                                        </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <table class="table">
                            <thead>
                                <th></th><th></th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>subtotal: </td>
                                    <td><strong>$ </strong></td> 
                                    <td><p><c:out value="${subtotal}"/></p></td>
                                </tr>
                                <tr>
                                    <td>sales tax: </td>
                                    <td><strong>$ </strong></td>
                                    <td><p><c:out value="${salesTax}"/></p></td>
                                </tr>
                                <tr>
                                    <td>shipping cost: </td>
                                    <td><strong>$ </strong></td>
                                    <td> <p id="shippingcost" name="shippingCost"></p></td>
                                </tr>
                                <tr>
                                    <td>Total Price: </td>
                                    <td><strong>$ </strong></td>
                                    <td><p id="total" name="total"></p></td>
                                </tr>
                                             <script>
                                                function shippingFunction(){
                                                    var cost = document.getElementById("shippingOption").value;
                                                    cost = (parseFloat(cost)*100)/100;
                                                    document.getElementById("shippingcost").innerHTML = cost;
                                                    var x = <c:out value="${subtotal}"/>;
                                                    var z = <c:out value="${salesTax}"/>;
                                                    x += (cost + z);
                                                    document.getElementById("total").innerHTML = x;
                                                }
                                            </script>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
<div class="col-sm-1"></div>
</div>
</form>
