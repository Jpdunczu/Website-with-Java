
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<form action="Authenticator" method="post">
<input type="hidden" name="action" value="login">
<div class="modal fade" id="myLogin" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header text-center" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h2><span class="glyphicon glyphicon-lock"></span> Login</h2>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
            <c:if test="${ exists == true }"><div class="alert alert-warning"><strong>Log In: </strong> <c:out value="${userExists}"/></div></c:if>
        
          
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
              <button type="submit" value="login" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
          
            <c:if test="${ loggedIn == false }"> <div class="alert alert-warning"><strong>ERROR: </strong> <c:out value="${message}"/></div></c:if>
        
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
          <p>Not a member? <a href="registrationPage.jsp">Sign Up</a></p>
          <p>Forgot <a href="forgotPassword.jsp">Password?</a></p>
        </div>
      </div>
      
    </div>
  </div>   
</form>
          
