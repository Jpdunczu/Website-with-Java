<%-- 
    Document   : users
    Created on : Aug 19, 2016, 1:15:26 AM
    Author     : joshuaduncan
--%>
<title>Users List</title>

<form action="AdminServlet" method="post">
<div class="container-fluid">
    <div class="col-sm-1"></div>
    <div class="col-sm-10">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3><div class="text-primary">Admin Console</div></h3>
                <button type="submit" class="btn btn-danger" name="action" value="backToAdmin">Back to Console</button>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Username:</th>
                                <th>First name:</th>
                                <th>Last name:</th>
                                <th>Email:</th>
                                <th>Email List:</th>
                                <th>Customer:</th>
                                
                            </tr>
                        </thead>
                            
                        <tbody>
                            <c:forEach var="users" items="${users}">
                                <tr>
                                    <td><input type="text" class="form-control" name="username" value="<c:out value='${users.getUsername()}'/>" disabled></td>
                                    <td><input type="text" class="form-control" name="firstname" value="<c:out value='${users.getFirstName()}'/>"></td>
                                    <td><input type="text" class="form-control" name="lastname" value="<c:out value='${users.getLastName()}'/>"></td>
                                    <td><input type="email" class="form-control" name="email" value="<c:out value='${users.getEmail()}'/>"></td>
                                    <td><c:if test="${users.getEmailList() == 1}"> YES</c:if><c:if test="${users.getEmailList() == 0}"> NO</c:if></td>
                                    <td><c:out value="${users.getCustomer()}"/></td>
                                    <td><button type="submit" class="btn btn-default " name="action" value="editUser">Edit User</button></td>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div> 
            </div>
        </div>
    </div>
<div class="col-sm-1"></div>
</div>

</form>