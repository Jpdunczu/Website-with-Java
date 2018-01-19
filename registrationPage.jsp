<%-- 
    Document   : registrationPage
    Created on : May 28, 2016, 1:23:24 AM
    Author     : joshuaduncan
--%>
<title>Register</title>

<div class="container-fluid">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="navbar-header">
                <div class="navbar-brand">
                <button class="btn-link" data-toggle="modal" href="#myModal">Store Name</button>
                </div>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="index.jsp"><button class="btn-link"> Home</button></a></li>
            </ul>  
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#myLogin" data-toggle="modal"><button class="btn-link"><span class="glyphicon glyphicon-log-in"></span>   Login</button></a></li>
            </ul>
        </nav>
</div>
    

<form action="AccountCreator" method="post">         
<div class="container-fluid">        
    <div class="col-sm-1"></div>
        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h3>Personal Information</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group"><input type="text" class="form-control" name="firstName" value="<c:out value='${firstName}'/>" placeholder="First Name" required></div>
                    <div class="form-group"><input type="text" class="form-control" name="lastName" value="<c:out value='${lastName}'/>" placeholder="Last Name" required></div>
                    <div class="form-group"><input type="email" class="form-control" name="email" value="<c:out value='${email}'/>" placeholder="E-Mail" required></div> 
                    <div class="checkbox" name="emailList"><label><input type="checkbox" name="emailList">Sign up to receive our monthly newsletter.</label></div>
                </div>
                    <div class="panel-footer">
                    <a data-toggle="collapse" href="#optional"><h3 class="text-center">Optional Information</h3></a>
                    <div id="optional" class="panel-collapse collapse">
                            
                            <div class="form-group">
                                <label for="phne">Phone: <p class="text-danger"><c:out value="${errorMessagePhone}"/></p></label>
                                <input type="tel" class="form-control" id="phne" name="phone" value="<c:out value='${phone}'/>" placeholder="Phone Number">
                            </div>
                            
                            <div class="form-group">
                                <label for="add">Address: <p class="text-danger"><c:out value="${errorMessageAddress}"/></p></label>
                                <input type="text" class="form-control" id="add" name="address" value="<c:out value='${address}'/>" placeholder="Address">
                           
                        
                                    <div class="col-xs-5">
                                        <label>City: </label>
                                        <input type="text" class="form-control" id="city" name="city" value="<c:out value='${city}'/>" placeholder="City">
                                    </div>
                                    
                                    <div class="col-xs-2">
                                        <label>State: </label>
                                        <select class="form-control" name="state" id="state" placeholder="">
                                            <option><c:out value="${state}"/></option>
                                            <option>AB</option><option>AE</option><option>AK</option><option>AL</option><option>AR</option><option>AS</option><option>AZ</option>
                                            <option>BC</option><option>CA</option><option>CO</option><option>CT</option><option>DC</option><option>DE</option><option>FL</option>
                                            <option>FM</option><option>GA</option><option>GU</option><option>HI</option><option>IA</option><option>ID</option><option>IL</option>
                                            <option>IN</option><option>KS</option><option>KY</option><option>LA</option><option>MA</option><option>MB</option><option>MD</option>
                                            <option>ME</option><option>MH</option><option>MI</option><option>MN</option><option>MO</option><option>MP</option><option>MS</option>
                                            <option>MT</option><option>NB</option><option>NC</option><option>ND</option><option>NE</option><option>NH</option><option>NJ</option>
                                            <option>NL</option><option>NM</option><option>NS</option><option>NT</option><option>NU</option><option>NV</option><option>NY</option>
                                            <option>OH</option><option>OK</option><option>ON</option><option>OR</option><option>PA</option><option>PE</option><option>PR</option>
                                            <option>PW</option><option>QC</option><option>RI</option><option>SC</option><option>SD</option><option>SK</option><option>TN</option>
                                            <option>TX</option><option>UT</option><option>VA</option><option>VI</option><option>VT</option><option>WA</option><option>WI</option>
                                            <option>WV</option><option>WY</option><option>YT</option>
                                        </select>
                                    </div>
               
                        <div class="col-xs-4">
                            <label>Zip: </label>
                            <input type="text" class="form-control" id="zip" name="zipCode" value="<c:out value='${zipCode}'/>" placeholder="Zip">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<div class="col-sm-1"></div>     
<div class="col-sm-5">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h3>Account Information</h3>
        </div>
        <div class="panel-body">
            <div class="form-group"><input type="text" class="form-control" name="username" value="<c:out value='${userName}'/>" placeholder="Username" required></div>
                <div class="form-group"><input type="password" class="form-control" name="password" placeholder="Password" required></div>
                    <dl>
                        <dt>Password</dt> 
                        <dd>- must be at least 8 characters long</dd>
                        <dd>- must contain at least one digit</dd>
                        <dd>- cannot contain your name</dd>
                        <dt>We will never ask for your password</dt>
                    </dl>
                    <c:if test="${pwError == true}">
                        <div class="alert alert-danger text-center"><c:out value="${passwordError}"/></div>
                    </c:if>
                    <c:if test="${exists == true}">
                        <div class="alert alert-warning text-center"><c:out value="${userExists}"/></div>
                    </c:if> 
        </div>
        <div class="panel-footer">
            <button type="submit" class="btn btn-info btn-block" name="action" value="create">Register</button>
        </div>
    </div>        
</div>
</div>

</form>

        