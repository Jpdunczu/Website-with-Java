<%-- 
    Document   : tester
    Created on : Aug 3, 2016, 3:50:38 PM
    Author     : joshuaduncan
--%>
<title>Error Processing your Order/title>
    
<form action="OrderManager" method="post">
 
    <!-- NAVIGATION BAR -->
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        
      </ul>
      <ul class="nav navbar-nav navbar-right">
          <li><a href="CartServlet?action=viewCart"><span class="glyphicon glyphicon-shopping-cart"></span><span class="badge"><c:if test="${cartCount > 0}"><c:out value="${cartCount}"/></c:if></span></a></li>
      </ul>
        
    </div>
</nav>
</div>    
</div>

<div class="container-fluid">
    <div class="col-sm-2"></div>
    <div class="col-sm-8">
    <div class="panel panel-default">
        <div class="panel-body">
                <div class="alert alert-danger text-center"><strong><h4>ERROR!</h4></strong> there was an error processing the order. Please return to checkout by clicking the button below.</div>
                
                <button type="submit" class="btn btn-danger btn-block" name="action" value="back">Return to Checkout</button>
        </div>
    </div>
    </div>
    <div class="col-sm-2"></div>
</div>

</form>
   

