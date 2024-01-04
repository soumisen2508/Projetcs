<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New User</title>
<%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<%@include file="components/errorPage.jsp" %>
<div class="container-fluid">
<div class="row mt-5">
<div class="col-md-4 offset-md-4">
<div class="card">
<div class="card-body px-5">
<center><img src="img/add-friend.png" style="height:80px; width:80px;"></center>
<h3 class="text-center my-3">Sign Up Here</h3>
<form action="RegisterServlet" method="post">
<div class="form-group">
    <label for="name">User Name</label>
    <input name="user_name" type="text" class="form-control" id="name" placeholder="Enter here" aria-describedby="nameInput">
  </div>
<div class="form-group">
    <label for="email">E-mail Address</label>
    <input name="user_email" type="email" class="form-control" id="email" placeholder="Enter here" aria-describedby="emailInput">
  </div> 
<div class="form-group">
    <label for="password">Password</label>
    <input name="user_password" type="password" class="form-control" id="password" placeholder="Enter here" aria-describedby="passwordInput">
  </div>
 <div class="form-group">
    <label for="phone">Contact No</label>
    <input name="user_phone" type="number" class="form-control" id="phone" placeholder="Enter here" aria-describedby="contactlInput">
  </div>
 <div class="form-group">
    <label for="address">Address</label>
    <textarea name="user_address" style="height:150px;" class="form-control" placeholder="Enter your address"></textarea>
  </div>
  <a href="login.jsp" class="text-center d-block mb-2">Already a customer? Click here</a>
 <div class="container text-center">
 <button class="btn btn-outline-success">Register</button>
 <button class="btn btn-outline-warning">Reset</button>
 </div>
</form>
</div>
</div>
</div>
</div>
</div>
</body>
</html>