<%-- 
    Document   : cart
    Created on : Jun 10, 2016, 2:54:10 PM
    Author     : joshuaduncan
--%>

<title>Shopping Cart</title>
<div class="container-fluid">
    <nav class="navbar navbar-default">
        
            <div class="navbar-header">
                <div class="navbar-brand">
                <button class="btn-link" data-toggle="modal" href="#myModal">Store Name</button>
                </div>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li class="active"><a><button type="" class="btn-link"><span class="glyphicon glyphicon-shopping-cart"></span></button></a></li>
                    
                </ul>
<form action="indexNavigation" method="post">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a><button type="submit" class="btn-link" name="action" value="home"><span class="glyphicon glyphicon-home"></span> Home</button></a></li>
                        <li><a><button type="submit" class="btn-link" name="action" value="sales">Sales</button></a></li>
                        <li><a><button type="submit" class="btn-link" name="action" value="featured">Featured Items</button></a></li>
                        <li><a><button type="submit" class="btn-link" name="action" value="top">Top Sellers</button></a></li>
                        <li><a><button type="submit" class="btn-link" name="action" value="gifts">Gifts</button></a></li>
                    </ul>
</form>
            </div>
        
    </nav>
</div>


<c:if test="${ cartItem == false || cartItem == null }">
    <div class="container-fluid">
        <div class="col-sm-2"></div>
            <div class="col-sm-8">
                <div class="alert alert-info text-center">
                    <strong>Sorry!</strong> Your shopping cart is currently empty!
                </div>
            </div>
        <div class="col-sm-2"></div>
    </div>
</c:if>
   

<c:if test="${ cartItem == true }">
    <div class="container-fluid">
        <div class="col-sm-2"></div>
            <div class="progress col-sm-8">
                <div class="progress-bar" role="progressbar" aria-valuenow="25" 
                    aria-valuemin="0" aria-valuemax="100" style="width: 25%;">
                    25% Complete
                </div>
            </div>
        <div class="col-sm-2"></div>
    </div>
    
    
    <c:if test="${ newItemAdded == true }">
        <div class="container-fluid">
            <div class="col-sm-4"></div>
                <div class="col-sm-4">
                    <div class="alert alert-success text-center">
                        <strong>Success!</strong> The item has been added to your shopping cart!
                    </div>
                </div>
            <div class="col-sm-4"></div>
        </div>
    </c:if>
    
    
    <c:if test="${ hasItem == true }">
        <div class="container-fluid">
            <div class="col-sm-3"></div>
                <div class="col-sm-6">
                    <div class="alert alert-warning text-center">
                        <strong>You already have this item in your shopping cart!</strong> 
                        <p>Would you like to change the quantity?</p>
                    </div>
                </div>
            <div class="col-sm-3"></div>
        </div>
    </c:if>
    
    <div class="container-fluid">
    <div class="col-sm-1"></div>   
    <div class="col-sm-10">
        <div class="table-responsive">
            <table class="table">
                <thead>
                    <tr>
                        <th>Qty</th>
                        <th>Size</th>
                        <th>Product</th>
                        <th>Description</th>
                        <th>Product Code</th>
                        <th>Price</th>
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
                        <td><img src="<c:out value='${cartItems.product.productImage}'/>" 
                                 class="img-thumbnail" alt="<c:out value='${cartItems.product.productName}'/>" 
                                 style="width:150px;height:150px;"></td>
                        <td><div class="text-info"><c:out value="${cartItems.product.productDesc}"/></div></td>
                        <td><input type="hidden" name="productID" value="<c:out value='${cartItems.product.productID}'/>"><c:out value="${cartItems.product.productID}"/></td>
                        <td><c:out value="${cartItems.getCartCost()}"/></td>
                        <td>
                            <form action="CartServlet" method="post">
                            <div class="form-group">
                            <button type="submit" class="btn btn-danger" name="action" value="remove">Remove</button>  
                            </div>
                            
                            <div class="form-group">
                            <button type="submit" class="btn btn-success" name="action" value="update">Update</button>
                            </div>
                            </form>
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
                                    Subtotal:  <strong>$ <c:out value="${subtotal}"/></strong>
                                </div>
                                <form action="OrderManager" method="post">
                                    <input type="hidden" name="action" value="checkout">
                                    <input type="submit" class="btn btn-info" name="action" value="Checkout">
                                </form>
                            </td>
                        </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-sm-1"></div>
</div>
    </c:if>
</div>


    