<%-- 
    Document   : thanks
    Created on : Jun 14, 2016, 3:10:08 PM
    Author     : joshuaduncan
--%>
<title>Thanks</title>

<div class="container-fluid">
    <nav class="navbar navbar-default navbar-fixed">
        <div class="navbar-header">
            <div class="navbar-brand">
            <button class="btn-link" data-toggle="modal" href="#myModal">Store Name</button>
            </div>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="index.jsp">Home</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#myLogin" data-toggle="modal"><span class="glyphicon glyphicon-log-in"></span>   Login</a></li>
        </ul>
    </nav>
</div>

<div class="container-fluid">
    <div class="col-sm-1"></div>
        <c:if test="${create == true}"><div class="alert alert-success"><strong>SUCCESS!:</strong><c:out value="${accountCreated}"/></div></c:if>
        <c:if test="${emailListJoined == true}"><div class="alert-success"><strong>Thank You!</strong><c:out value="${thanksForJoining}"/></div></c:if>
    <div class="col-sm-1"></div>
</div>

<div class="container-fluid">
    <div class="col-sm-1"></div>
        <div class="col-sm-10">
            <div class="well">
                <h3 class="text-center">Congratulations!</h3>
                <p>Your new account has been created.</p> 
                 <p>Once you log in you will be able to begin to browse our 
                    merchandise and add items to your shopping cart. 
                    Use the top navigation bar to search for merchandise.</p>
            </div>
        </div>
    <div class="col-sm-1"></div>
</div>

