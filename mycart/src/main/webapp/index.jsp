<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="mycart.helper.FactoryProvider" %>
<%@ page import="mycart.helper.Helper" %>
<%@ page import="mycart.dao.ProductDao" %>
<%@ page import="mycart.dao.CategoryDao" %>
<%@ page import="java.util.List" %>
<%@ page import="mycart.entities.Product" %>
<%@ page import="mycart.entities.Category" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyCart - Home Page</title>
<%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<%@include file="components/errorPage.jsp" %>
<%@include file="components/message.jsp" %>
<div class="container-fluid">
<div class="row mt-3 mx-2">
<%
String cat = request.getParameter("category");
ProductDao dao = new ProductDao(FactoryProvider.getFactory());
List <Product> list = null;
if(cat==null || cat.trim().equals("all")){
	list = dao.getAllProducts();
}else{
	int cid = Integer.parseInt(cat.trim());
	list=dao.getAllProductsById(cid);
}

CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
List <Category> clist = cdao.getCategories();
%>
<!-- show categories -->
<div class="col-md-2">
<div class="list-group mt-4">
<a href="index.jsp?category=all" class="list-group-item list-group-item-action active">
    All Categories</a>
<%
for(Category c:clist){
	if(cat==null||cat.trim().equals("all")){
		%>
		<a href="index.jsp?category=<%=c.getCategoryId() %>" class="list-group-item list-group-item-action"><%=c.getCategoryTitle() %></a>
		<%
	}
	else if(Integer.parseInt(cat.trim())==c.getCategoryId()){%>
	
		<a href="index.jsp?category=<%=c.getCategoryId() %>" class="list-group-item list-group-item-action active"><%=c.getCategoryTitle() %></a>
<% }else{%>
	<a href="index.jsp?category=<%=c.getCategoryId() %>" class="list-group-item list-group-item-action"><%=c.getCategoryTitle() %></a>
<% }
	
}
%>
</div>
</div>
<!-- show products -->
<div class="col-md-10">
<!-- row -->
<div class="row mt-4">
<div class="col-md-12">
<div class="card-columns">
<!-- traversing products -->
<%
for(Product p:list){
%>
<!-- product card -->
<div class="card product-card">
<div class="container text-center">
<img src="img\products\<%=p.getpPhoto() %>" style="max-height:200px; max-width:90%; width:auto;" class="card-img-top  m-2"/>
</div>
<div class="card-body">
<h5 class="card-title"><%=p.getpName() %></h5>
<p class="card-text"><%=Helper.get10Words(p.getpDesc()) %></p>
</div>
<div class="card-footer text-center">
<button class="btn custom-bg text-white" onclick="add_to_cart(<%=p.getpId()%>, '<%=p.getpName()%>' , <%=p.getPriceAfterApplyingDiscount() %>)">Add to Cart</button>
<button class="btn btn-outline-success">&#X20B9; <%=p.getPriceAfterApplyingDiscount() %>/- <span class="text-secondary discount-label"> &#X20B9; <%=p.getpPrice() %> <%=p.getpDiscount()%>% off</span></button>
</div>
</div>

<%
}
if(list.size()==0)
{
	out.println("<h3>No item in this category!</h3>");
}

%>
</div>
</div>
</div>
</div>
</div>
</div>

<%@include file="components/common_modals.jsp" %>

</body>
</html>