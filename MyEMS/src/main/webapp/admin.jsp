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
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>
<%@include file="components/common_css_js.jsp" %>
</head>
<body>
<%@include file="components/navbar.jsp" %>
<br>
<div class="row">
<div class="container">
<h3 class="text-center">List of Employees</h3>
<hr>
<div class="container text-left">
<a href="EmployeeServlet?action=new"><button class="btn btn-success">Add New Employee</button></a>
</div>
<br>
<%
EmployeeDao dao = new EmployeeDao();
List <Employee> list = dao.selectAllEmployees();
%>
<!-- show employees -->
<table class="table table-bordered text-center">
<thead>
<tr>
<th>ID</th>
<th>Name</th>
<th>Email</th>
<th>Joining Date</th>
<th>Salary</th>
<th>Actions</th>
</tr>
</thead>
<tbody>
<!-- traversing employees -->
<%
for(Employee e:list){
%>
<tr>
<td><%=e.getEmpId()%></td>
<td><%=e.getEmpName() %></td>
<td><%=e.getEmpEmail() %></td>
<td><%=e.getEmpJoinDate() %></td>
<td><%=e.getEmpSalary() %></td>
<td class="text-center"><a href="EmployeeServlet?action=edit&empId=<%=e.getEmpId()%>">Edit</a>
<a href="EmployeeServlet?action=delete&empId=<%=e.getEmpId()%>">Delete</a></td>
</tr>
<%
}
if(list.size()==0)
{
	out.println("<h3>No Employee added till now!</h3>");
}
%>
</tbody>
</table>
</div>
</div>
</body>
</html>