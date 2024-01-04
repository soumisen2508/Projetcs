<%
User user=(User)session.getAttribute("current-user");
if(user==null){
	session.setAttribute("errorPage", "You're not logged in! Please login first to access Checkout page.");
	response.sendRedirect("login.jsp");
	return;
}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>
<%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="container">
<div class="row mt-5">
<div class="col-md-6">
<!-- card -->
<div class="card">
<div class="card-body">
<h3 class="text-center mb-5">Selected items</h3>
<div class="cart-body"></div>
</div>
</div>
</div>
<div class="col-md-6">
<!-- form details -->
<div class="card">
<div class="card-body">
<h3 class="text-center mb-5">Your details for order</h3>
<form action="#!">
<div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input value="<%= user.getUserEmail() %>" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="name">Name</label>
    <input value="<%=user.getUserName() %>" type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="Enter your name">
  </div>
  <div class="form-group">
    <label for="exampleFormControlTextarea1">Shipping Address</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" placeholder="Enter your address" rows="3"></textarea>
  </div>
  <div class="container text-center">
  <a href="CheckOutServlet" class="btn btn-outline-success">Order Now</a>
  <a href="index.jsp" class="btn btn-outline-primary">Continue shopping</a>
  </div>
</form>
</div>
</div>
</div>
</div>
</div>
<%@include file="components/common_modals.jsp" %>
</body>
</html>