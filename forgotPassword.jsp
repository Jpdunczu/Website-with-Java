<%-- 
    Document   : forgotPassword
    Created on : Aug 4, 2016, 10:16:28 AM
    Author     : joshuaduncan
--%>
<title>Forgot Password</title>

<div class="container-fluid">
    <div class="col-sm-1"></div>
        <nav class="navbar navbar-default navbar-fixed">
            <div class="navbar-header">
                <a class="navbar-brand" data-toggle="modal" href="#myModal">Store Name</a>
            </div>
    <!-- NAVIGATION BAR -->
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                  <li class="active"><a href="index.jsp">Home</a></li>
                </ul>
            </div>
        </nav>
    <form action="indexNavigation" method="post">
<div class="container-fluid">
    <div class="col-sm-3"></div>
        <div class="col-sm-6">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h2>Forgot Password:</h2>
                </div>
                <div class="panel-body">
                    <div class="text-info">Please enter your username and click the Request Password button.</div>

                    <label for="user">Username: </label>
                    <input type="text" class="form-control" id="user" name="pwRequestUser"> 

                </div>
                <div class="panel-footer">
                    <button type="submit" class="btn btn-primary btn-block" name="action" value="requestNewPW">Request Password</button>
                </div>
            </div>
        </div>
    <div class="col-sm-3"></div>
</div>
    </form>

