<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
Employee employee=(Employee)session.getAttribute("current-employee");
if(employee==null){
	session.setAttribute("errorPage", "You're not logged in! Please login first.");
	response.sendRedirect("login.jsp");
	return;
}
else{
	if(employee.getEmpType().equals("normal")){
		session.setAttribute("errorPage", "You're not Admin! Accessing this page is prohibited.");
		response.sendRedirect("login.jsp");
		return;
	}
}
%>
<html>
<head>
<title>Navigation Bar</title>
<%@include file="components/common_css_js.jsp" %>
<meta name="viewport" content="width=device-width, initial-scale=1">

</style>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<%@include file="components/errorPage.jsp" %>
<form action="EmployeeServlet?action=search" method="post">
<nav class="navbar navbar-inverse">
<div class="container-fluid search mt-4" style="width:350px">
<div class="md-form active-purple active-purple-2 my-3">
<input class="form-control" style="width:300px" type="text" name="searchString" placeholder="Type your search keyword" aria-label="Search">
</div>
<button type="submit" class="btn btn-primary">
    <i class="fa fa-search-plus"></i>
  </button>
</div>
</nav>
</form>
<div class="container">
</div>
</body>
</html>