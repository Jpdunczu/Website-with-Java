<%-- 
    Document   : productDisplay
    Created on : Jul 27, 2016, 5:36:49 PM
    Author     : joshuaduncan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">
    <div class="col-sm-1"></div>
        <div class="row">
            <div class="col-sm-2"></div>
            <div class="col-sm-6">       
                <div class="row"> 
                    <c:forEach items="${products}" var="products">
                        <div class="col-md-4">
                            <h3><c:out value="${products.productName}"/></h3>
                            <a href="#<c:out value='${products.productID}'/>" value="<c:out value='${products.productImage}'/>" class="thumbnail" data-toggle="modal">
                            <img class="img-responsive" src="<c:out value='${products.productImage}'/>" alt="<c:out value='${products.productName}'/>" style="width:150px;height:150px">
                            </a>
                            <div class="modal fade" id="<c:out value='${products.productID}'/>" role="dialog">
                                <div class="modal-dialog">
                                <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title"><c:out value="${products.productName}"/></h4>
                                </div>
                                <div class="modal-body">
                                    
                                    <img class="img-responsive" src="<c:out value='${products.productImage}'/>" alt="<c:out value='${products.productName}'/>" style="width: 200px;height: 200px;"> 
                                        <div class="well well-lg">
                                            <label>Description: </label>
                                            <p><c:out value="${products.productDesc}"/></p>
                                            <label>Product Code: </label>
                                            <p><c:out value="${products.productID}"/></p> 
                                        </div>
                                        
                            <c:if test="${ login == false }"><div class="alert alert-info"><strong>Price: </strong>Login or create an account to see the special members only price.</div></c:if>
                            <c:if test="${ login == true }"><label>Price: <c:out value="${ products.productRetail }"/></label></c:if>
                        
                        <div class="modal-footer">
                            <form action="CartServlet" method="post" >
                                    <c:if test="${ login == true }">
                                        <input type="hidden" name="productID" value="<c:out value='${products.productID}'/>">
                                        
                                            <div class="form-group">
                                                <div class="col-xs-4">
                                                    <label for="qty">Quantity: </label>
                                                        <select class="form-control" id="qty" name="quantity">
                                                            <option>1</option><option>2</option><option>3</option><option>4</option><option>5</option>
                                                        </select>
                                                    Please Contact Us for quantity greater than 5.
                                                </div>
                                            </div>  
                                        <div class="form-group">
                                            <div class="col-xs-4">
                                                <label for="size">Size: </label>
                                                    <select class="form-control" id="size" name="size">
                                                        <option>XS</option><option>S</option><option>M</option><option>L</option><option>XL</option><option>2XL</option>
                                                    </select>
                                            </div>
                                        </div>
                                        
                                            <button type="submit" class="btn btn-primary btn-block" name="action" value="addToCart">Add to Cart</button>
                                        
                                       
                                   </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>  
    </div>
</c:forEach>
</div>   
</div>
