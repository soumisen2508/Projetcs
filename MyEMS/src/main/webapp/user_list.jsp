<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="myems.entities.Employee" %>
<%@ page import="myems.dao.EmployeeDao" %>
<%@ page import="java.util.List" %>
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
List <Employee> searchList = (List)request.getAttribute("searchListEmployee");
List <Employee> salaryList = (List)request.getAttribute("salaryListEmployee");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User List</title>
<%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<div class="row">
<div class="container mt-3">
<%
if(salaryList!=null){
%>
<h3 class="text-center">Annual Salary of Employees</h3>
<%
}else{
%>
<h3 class="text-center">Search Result of Employees</h3>
<%
}
%>
<hr>
<br>
<!-- show employees -->
<table class="table table-bordered text-center">
<thead>
<tr>
<th>ID</th>
<th>Name</th>
<%if(searchList!=null) {
%>
<th>Join Date</th>
<%
} 
%>
<th>Salary</th>
</tr>
</thead>
<tbody>
<!-- traversing employees -->
<%
if(salaryList!=null){
	for(Employee e:salaryList){
%>
<tr>
<td><%=e.getEmpId()%></td>
<td><%=e.getEmpName() %></td>
<td><%=e.getEmpSalary() %></td>
</tr>
<%
}
}
	else if(searchList !=null) {
		for(Employee e:searchList){	
%>
<tr>
<td><%=e.getEmpId()%></td>
<td><%=e.getEmpName()%></td>
<td><%=e.getEmpJoinDate()%></td>
<td><%=e.getEmpSalary() %></td>
</tr>
<%	}
	}else{
	out.println("<h3>No Employee found till now!</h3>");
}
%>
</tbody>
</table>
</div>
</div>
</body>
</html>