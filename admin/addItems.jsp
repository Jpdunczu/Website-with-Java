<%-- 
    Document   : addItems
    Created on : Aug 15, 2016, 11:06:33 PM
    Author     : joshuaduncan
--%>

<form action="FileUploadServlet" method="post" enctype="multipart/form-data" >
    <div class="container-fluid">
        <div class="col-sm-2"></div>
        <div class="col-sm-8">
            <div class="text-center"><h3>Add/Edit Items</h3></div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Add new item</h3>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="file">File:</label>
                        <input type="file" class="form-control" name="file" id="file" required/>
                    </div>
                    <div class="text text-center text-primary"><h4><b>Product Details</b></h4></div>
                    <div class="form-group col-xs-4">
                        <label for="productName">Name:</label>
                        <input type="text" class="form-control" name="productName" id="productName" placeholder="Product Name" required/>
                    </div>
                    <div class="form-group col-xs-6">
                        <label for="description">Description:</label>
                        <textarea class="form-control" name="productDescription" 
                                  rows="3" id="description" placeholder="Short description of the product..." required></textarea>
                    </div>
                    <div class="form-group col-xs-2">
                        <label for="cost">Cost:</label>
                        <input type="text" class="form-control" name="productCost" id="cost" placeholder="0.00" required/>
                    </div>
                    <div class="form-group col-xs-2">
                        <label for="retail">Retail:</label>
                        <input type="text" class="form-control" name="productRetail" id="retail" placeholder="0.00" required/>
                    </div>
                    <div class="form-group col-xs-3">
                        <label for="type">Type:</label>
                        <select class="form-control" name="productType" id="type" required>
                            <option value="gift">gift</option>
                            <option value="top">top seller</option>
                            <option value="feat">featured item</option>
                        </select>
                    </div>
                    <div class="form-group col-xs-2">
                        <label for="sale">on Sale?</label>
                        <select class="form-control" name="sale" id="sale" required>
                            <option value="no">NO</option>
                            <option value="yes">YES</option>
                        </select>
                    </div>
                    <div class="form-group col-xs-2">
                        <label for="salePrice">Sale price:</label>
                        <input type="text" class="form-control" name="salePrice" placeholder="0.00"/>
                    </div>
                </div>
                <div class="panel-footer">
                    <button type="submit" class="btn btn-primary btn-block" name="action" value="newItem">Add New Item</button>
                </div>
            </div>
        </div>
        <div class="col-sm-2"></div>
    </div>
</form>
<form action="AdminServlet" method="post">
    <div class="container-fluid">
        <div class="text-center text-primary"><h3><b>Edit Products</b></h3></div>
        <div class="table-responsive">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Name:</th>
                        <th>ID:</th>
                        <th>Item:</th>
                        <th>Description:</th>
                        <th>Cost:</th>
                        <th>Retail:</th>
                        <th>Sale?</th>
                        <th>Sale Price:</th>
                        <th>Type:</th>
                        <td></td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="products" items="${products}">
                    <tr>
                        <td><c:out value="${products.getProductName()}"/></td>
                        <td><c:out value="${producst.getProductID}"/></td>
                        <td><img src="<c:out value='${products.productImage}'/>" 
                                 class="img-thumbnail" alt="<c:out value='${products.productName}'/>" 
                                 style="width:80px;height:80px;"></td>
                        <td><c:out value="${products.getProductDesc()}"/></td>
                        <td><c:out value="${products.getProductCost()}"/></td>
                        <td><c:out value="${products.getProductRetail()}"/></td>
                        <td><c:out value="${products.getSale()}"/></td>
                        <td><c:out value="${products.getSalePrice()}"/></td>
                        <td><c:out value="${products.getProductType()}"/></td>
                        <td><button type="submit" class="btn btn-default" name="edit" value="<c:out value='${products.getProductID()}'/>">Edit</button></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</form>
