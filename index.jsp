<%-- 
    Document   : landing
    Created on : May 26, 2016, 8:10:45 PM
    Author     : joshuaduncan
--%>
<title>Store Name</title>

<body style="background-color: #f2f2fa">
<div class="container-fluid">

        <nav class="navbar navbar-default navbar-fixed">
            <div class="navbar-header">
                <div class="navbar-brand">
                    <button class="btn-link" data-toggle="modal" href="#myModal" style="color: #009999; margin-top: 8px;">Store Name</button>
                </div>
            </div>
<!-- NAVIGATION BAR -->
<form action="indexNavigation" method="post">
            <ul class="nav navbar-nav">
                <li class="active"><a><button class="btn-link" style="color: black; margin-top: 8px;"> Home</button></a></li>
                <li><a><button type="submit" class="btn-link" name="action" value="sales" style="color: #330066; margin-top: 8px;">Sales</button></a></li>
                <li><a><button type="submit" class="btn-link" name="action" value="featured" style="color: #330066; margin-top: 8px;">Featured Items</button></a></li>
                <li><a><button type="submit" class="btn-link" name="action" value="top" style="color: #330066; margin-top: 8px;">Top Sellers</button></a></li>
                <li><a><button type="submit" class="btn-link" name="action" value="gifts" style="color: #330066; margin-top: 8px;">Gifts</button></a></li>
            </ul>
</form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="registrationPage.jsp" ><button class="btn-link" style="color: #330066; margin-top: 8px;"><span class="glyphicon glyphicon-user"></span>  Sign Up</button></a></li>
                <li><a href="#myLogin" data-toggle="modal"><button class="btn-link" style="padding-right: 30px; color: #330066; margin-top: 8px;"><span class="glyphicon glyphicon-log-in"></span>   Login</button></a></li>
            </ul>
        </nav>

<div class="panel panel-default" style="background-color: azure; padding: 15px;">
<div id="sales" class="container-fluid">

    <div class="col-sm-2"></div>
    <div class="col-sm-8">
        <c:forEach var="sales" items="${products}">
                    <div id="mySales" class="carousel slide" data-ride="carousel">
                        <ol class="carousel-indicators">
                            <li data-target="<c:out value='${products.productID}'/>" data-slide-to="#" class="active"></li>
                        </ol>

                        <div class="carousel-inner" role="listbox">
                            <div class="item">
                                <img src="<c:out value='${products.productImage}'/>" alt="<c:out value='${products.productName}'/>">
                            </div>
                        </div>

                            <a class="left carousel-control" href="#mySales" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>

                            <a class="right carousel-control" href="#mySales" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                    </div>
        </c:forEach>
    </div>
    <div class="col-sm-2"></div>
</div>


<div class="panel-group">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="col-sm-5"></div>
            Sign up for our Newsletter!
        </div>
        <div class="panel-body">
            <div class="col-sm-5"></div>
            <form class="form-inline" roles="form">
            <input type="email" class="form-control"  name="email" placeholder="yourEmail@email.com">
            <form action="EmailServlet" method="post">
                <button type="submit" class="btn btn-default" name="action" value="join">Join</button> 
            </form>
        </div>
    </div>
</div>  
</div>
</div>
</body>