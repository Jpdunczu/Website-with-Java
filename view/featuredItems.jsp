<%-- 
    Document   : featuredItems
    Created on : Jun 16, 2016, 2:14:27 PM
    Author     : joshuaduncan
--%>

<title>Featured Items</title>

<div class="container-fluid">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="navbar-header">
                <a class="navbar-brand" data-toggle="modal" href="#myModal">Store Name</a>
            </div>
    <!-- NAVIGATION BAR -->
            <div class="collapse navbar-collapse" id="myNavbar">
<form action="indexNavigation" method="post">
                <ul class="nav navbar-nav">
                    <li><a><button type="submit" class="btn-link" name="action" value="home"><span class="glyphicon glyphicon-home"></span> Home</button></a></li>
                    <li><a><button type="submit" class="btn-link" name="action" value="sales">Sales</button></a></li>
                    <li><a><button type="submit" class="btn-link" name="action" value="gifts">Gifts</button></a></li>
                    <li><a><button type="submit" class="btn-link" name="action" value="top">Top Sellers</button></a></li>
                </ul>
</form>
                <form action="CartServlet" method="post">
                <ul class="nav navbar-nav navbar-right">
            <c:if test="${ login == false }">
                    <li class="dropdown">
                        <a class="dropdown-toggle glyphicon glyphicon-user" data-toggle="dropdown" href="#"><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="registrationPage.jsp"><span class="glyphicon glyphicon-user"></span>  Sign Up</a></li>
                            <li><a href="#myLogin" data-toggle="modal"><span class="glyphicon glyphicon-log-in"></span>   Login</a></li>
                        </ul>
                    </li>
            </c:if>
                    <li><a><button type="submit" class="btn-link" name="action" value="viewCart"><span class="glyphicon glyphicon-shopping-cart"></span><span class="badge"><c:if test="${cartCount > 0}"><c:out value="${cartCount}"/></c:if></span> Cart</button></a></li>
                </ul>
                </form>
            </div>
        </nav>
</div>

<%@ include file="/WEB-INF/jspf/productDisplay.jsp"%>

</div>

