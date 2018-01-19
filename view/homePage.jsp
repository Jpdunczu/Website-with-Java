<%-- 
    Document   : homePage
    Created on : Jun 15, 2016, 2:30:05 PM
    Author     : joshuaduncan
--%>
<title>Home Page</title>

<div class="container-fluid">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="navbar-header">
                <div class="navbar-brand">
                    <button class="btn-link" data-toggle="modal" href="#myModal">Store Name</button>
                </div>
            </div>
<!-- NAVIGATION BAR -->
<form action="indexNavigation" method="post">
            <ul class="nav navbar-nav">
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


<c:if test="${ login == true }">
    <div class="container-fluid">
        <div class="col-sm-1"></div> 
            <div class="col-sm-10">
                <div class="row">
                    <h2><strong>Welcome<strong> <c:out value="${firstName}"/></h2>
                    <div class="panel panel-default">
                        <div class="panel-heading text-center"><h2>Order History</h2>
                            <form action="indexNavigation" method="post">
                                <button type="submit" class="btn-link" name="action" value="logout"><span class="glyphicon glyphicon-lock"></span> Logout</button>
                            </form>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <td>Date:</td>
                                            <td>Order#:</td>
                                            <td>Status:<td>
                                            <td>Shipping:</td>
                                            <td>Total:</td>
                                        </tr>
                                    </thead>
                                    
                                    <c:forEach var="orders" items="${orders}">
                                    <tbody>
                                        <tr>
                                            <td><c:out value="${orders.getOrderDate()}"/></td>
                                            <td><button type="button" class="btn btn-link" data-toggle="modal" 
                                                        data-target="#orderNum"><c:out value="${orders.getOrderNumber()}"/></button></td>
                                            <td><c:out value="${orders.getOrderStatus()}"/></td>
                                            <td><c:out value="${customer.getBillingAddress()}"/></td>
                                            <td><c:out value="${orders.getShippingMethod()}"/></td>
                                            <td><c:out value="${orders.getOrderTotal()}"/></td>
                                        </tr>
                                    </tbody>
                                    </c:forEach>
                                    
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <div class="col-sm-1"></div>                 
    </div>
</c:if>


      <div id="orderNum" class="modal fade" role="dialog">
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                      <h4 class="modal-title text-center">Items</h4>
                  </div>
                  <div class="modal-body">
                    <div class="table-responsive">
                        <table class="table">
                            <c:forEach var="orders" items="${order}">
                                <tbody>
                                    <tr>
                                        <td><strong><c:out value="${order.cart.productCount}"/>x </strong></td>
                                        <td><c:out value="${order.cart.size}"/></td>
                                        <td><c:out value="${order.cart.product.productName}"/></td>
                                        <td><c:out value="${order.cart.product.productID}"/></td>
                                        <td><strong>$ </strong><c:out value="${order.cart.getCartCost()}"/></td>
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                  </div>
              </div>
          </div>
      </div>
          