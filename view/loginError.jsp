<%-- 
    Document   : loginError
    Created on : Aug 10, 2016, 10:05:12 PM
    Author     : joshuaduncan
--%>
<title>Login</title>

<div class="container-fluid">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="navbar-header">
                <div class="navbar-brand">
                <button class="btn-link" data-toggle="modal" href="#myModal">Store Name</button>
                </div>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>
            </ul>
        </nav>
</div>
            
<div class="container-fluid">
    <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="alert alert-danger">
                <p><strong>We're Sorry!</strong>
                The username or password you entered was incorrect! Please try again.</p>
            </div>
        </div>
    <div class="col-sm-2"></div>
</div>
<form action="Authenticator" method="post">
<div class="container-fluid">
    <div class="col-sm-3"></div>
    <div class="col-sm-6">
        
    <div class="panel panel-heading text-center">
        <h2><span class="glyphicon glyphicon-lock"></span> Login</h2>
    </div>
    <div class="panel-body">
            <div class="form-group">
                <label for="usrname"><span class="glyphicon glyphicon-user"></span> Username</label>
                <input type="text" class="form-control" value="<c:out value='${username}'/>" name="username" id="usrname" placeholder="Enter email">
            </div>
            <div class="form-group">
                <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                <input type="password" class="form-control" name="password" id="psw" placeholder="Enter password">
            </div>
            <div class="checkbox" name="rememberMe">
                <label><input type="checkbox" name="rememberMe" value="" checked>Remember me</label>
            </div>
                <button type="submit" name="action" value="login" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
    </div>
            <div class="panel-footer">
                <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
                <p class="text-right">Not a member? <a href="registrationPage.jsp">Sign Up</a></p>
                <p class="text-right">Forgot <a href="forgotPassword.jsp">Password?</a></p>
            </div>
    </div>
    <div class="col-sm-3"></div>
</div>
</form>