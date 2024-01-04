<%@ page import="mycart.entities.User" %>
<%
User user=(User)session.getAttribute("current-user");
if(user==null){
	session.setAttribute("errorPage", "You're not logged in! Please login first.");
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
<title>User Panel</title>
<%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<h1>This is Normal User Panel</h1>
<%@include file="components/common_modals.jsp" %>
</body>
</html>