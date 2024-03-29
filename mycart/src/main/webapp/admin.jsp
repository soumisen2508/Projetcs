<%@ page import="mycart.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="mycart.entities.Category" %>
<%@ page import="mycart.helper.Helper" %>
<%@ page import = "java.util.Map"%>
<%@ page import="mycart.dao.CategoryDao"%>
<%@ page import="mycart.helper.FactoryProvider"%>
<%
User user=(User)session.getAttribute("current-user");
if(user==null){
	session.setAttribute("errorPage", "You're not logged in! Please login first.");
	response.sendRedirect("login.jsp");
	return;
}
else{
	if(user.getUserType().equals("normal")){
		session.setAttribute("errorPage", "You're not Admin! Accessing this page is prohibited.");
		response.sendRedirect("login.jsp");
		return;
	}
}
 %>
 
 <%
        CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
        List<Category> list = cdao.getCategories();
        
        //getting count
        
        Map<String,Long> m= Helper.getCounts(FactoryProvider.getFactory());
        %>
 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>
<%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="container admin">
<div class="container-fluid mt-3">
<%@include file="components/message.jsp" %>
</div>
<div class="row mt-3">
<!-- first column -->
<div class="col-md-3">
<div class="card">
<div class="card-body text-center">
<div class="container">
<img style="max-width:125px;" class="img-fluid rounded-circle" src="img/team.png" alt="user_icon">
</div>
<h1><%=m.get("userCount") %></h1>
<h1 class="text-uppercase text-muted">Users</h1>
</div>
</div>
</div>
<!-- second column -->
<div class="col-md-3">
<div class="card">
<div class="card-body text-center">
<div class="container">
<img style="max-width:125px;" class="img-fluid rounded-circle" src="img/options.png" alt="categories_icon">
</div>
<h1><%=list.size() %></h1>
<h1 class="text-uppercase text-muted" style="width:230px;" >Categories</h1>
</div>
</div>
</div>
<!-- third column -->
<div class="col-md-3">
<div class="card">
<div class="card-body text-center">
<div class="container">
<img style="max-width:125px;" class="img-fluid rounded-circle" src="img/box.png" alt="product_icon">
</div>
<h1><%=m.get("productCount") %></h1>
<h1 class="text-uppercase text-muted">Products</h1>
</div>
</div>
</div>

<!-- fourth column -->
<div class="col-md-3">
<div class="card">
<div class="card-body text-center">
<div class="container">
<img style="max-width:125px;" class="img-fluid rounded-circle" src="img/order_icon.jpg" alt="order_icon">
</div>
<h1><%=m.get("orderCount") %></h1>
<h1 class="text-uppercase text-muted">Orders</h1>
</div>
</div>
</div>

</div>
<div class="row mt-3">
<!-- second row: first column -->
<div class="col-md-6">
<div class="card" data-toggle="modal" data-target="#add-category-modal">
<div class="card-body text-center">
<div class="container">
<img style="max-width:125px;" class="img-fluid rounded-circle" src="img/menu.png" alt="add_category_icon">
</div>
<h1 class="text-uppercase text-muted">Add Category</h1>
</div>
</div>
</div>
<!-- second row: second column -->
<div class="col-md-6">
<div class="card" data-toggle="modal" data-target="#add-product-modal">
<div class="card-body text-center">
<div class="container">
<img style="max-width:125px;" class="img-fluid rounded-circle" src="img/add-package.png" alt="add_product_icon">
</div>
<h1 class="text-uppercase text-muted">Add Product</h1>
</div>
</div>
</div>
</div>
</div>
<!-- add category modal -->

<!-- Modal -->
<div class="modal fade" id="add-category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModal" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg text-white">
        <h5 class="modal-title" id="exampleModalLable">Fill Category Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
        <form action="ProductOperationServlet" method="post">
        <input type="hidden" name="operation" value="addcategory">
        <div class="form-group">
        <input type="text" style="height:50px;" class="form-control" name="catTitle" placeholder="Enter category title" required />
        </div>
         <div class="form-group">
         <textarea style="height:150px;" class="form-control" placeholder="Enter category description" name="catDescription" required></textarea>
         </div>
         <div class="container text-center">
         <button class="btn btn-outline-success">Add Category</button>
         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
         </div>
        </form>
        
      </div>
    </div>
  </div>
</div>
<!-- ends here -->

<!-- add product modal -->

<!-- Modal -->
<div class="modal fade" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModal" aria-hidden="true">
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg text-white">
        <h5 class="modal-title" id="exampleModalLable">Fill Product Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        
        <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="operation" value="addproduct" />
        <!-- product name -->
        <div class="form-group">
        <input type="text" class="form-control" name="pName" placeholder="Enter title of product" required />
        </div>
        <!-- product description -->
         <div class="form-group">
         <textarea style="height:150px;" class="form-control" placeholder="Enter product description" name="pDesc" required></textarea>
         </div>
         <!-- product price -->
        <div class="form-group">
        <input type="number" class="form-control" name="pPrice" placeholder="Enter product price" required />
        </div>
        <!-- product discount -->
        <div class="form-group">
        <input type="number" class="form-control" name="pDiscount" placeholder="Enter product discount" required />
        </div>
        <!-- product quantity -->
        <div class="form-group">
        <input type="number" class="form-control" name="pQuantity" placeholder="Enter product quantity" required />
        </div>
        <!-- product category -->
        <div class="form-group">
        <select name="catId" class="form-control" id="">
        <%
        for(Category c:list){
        %>
        <option value="<%= c.getCategoryId() %>"><%=c.getCategoryTitle() %></option>
        <% } %>
        
        </select>
        </div>
        <!-- product file -->
        <div class="form-group">
        <label for="pPic">Select Product Picture</label>
        <br>
        <input type="file" id="pPic" name="pPic" required />
        </div>
         <div class="container text-center">
         <button class="btn btn-outline-success">Add Product</button>
         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
         </div>
        </form>
        
      </div>
    </div>
  </div>
</div>
<!-- ends here -->

<%@include file="components/common_modals.jsp" %>

</body>
</html>