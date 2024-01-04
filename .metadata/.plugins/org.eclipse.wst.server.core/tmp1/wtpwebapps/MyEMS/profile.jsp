<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="myems.entities.Employee" %>
<%@ page import="myems.dao.EmployeeDao" %>
    <%
Employee employee=(Employee)session.getAttribute("current-employee");
if(employee==null){
	session.setAttribute("errorPage", "You're not logged in! Please login first.");
	response.sendRedirect("login.jsp");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Home Page</title>
</head>
<body>
<%@include file="components/common_css_js.jsp" %>
<%@include file="components/navbar.jsp" %>
<div class="container text-center mt-4">
<center><img src="img\employee.png" style="max-height:200px; max-width:90%; width:auto;"/>
<h1 class="text-center">Hey <%=employee.getEmpName() %>! Sorry this page is under maintenance. Please contact Administrator. Thanks! </h1>
</center>
</div>
</body>
</html>