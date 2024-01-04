<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="myems.entities.Employee" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMS</title>
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
<%Employee employee=(Employee)session.getAttribute("current-employee");
Employee editEmployee=(Employee)request.getAttribute("employee");
if(editEmployee==null||employee==null){
	%>
<form action="RegisterServlet" method="post">
<h3 class="text-center my-3">Sign Up Here</h3>
<div class="form-group">
<fieldset class="form-group">
<label>Name</label><input type="text" class="form-control" placeholder="Enter here" name="empName" required="required">
</fieldset>
    </div>
<div class="form-group">
    <label for="email">E-mail Address</label>
    <input name="empEmail" type="email" class="form-control" placeholder="Enter here" aria-describedby="emailInput" required="required">
  </div> 
<div class="form-group">
    <label for="password">Password</label>
    <input name="empPassword" type="password" class="form-control" placeholder="Enter here" aria-describedby="passwordInput">
  </div>
 <div class="form-group">
    <label for="joindate">Join Date</label>
    <input name="empJoinDate" type="date" class="form-control" placeholder="Enter here" aria-describedby="joinDateInput">
  </div>
 <div class="form-group">
    <label for="salary">Salary</label>
    <input name="empSalary"  type="number" class="form-control" placeholder="Enter here" aria-describedby="salarynput">
  </div>
  <% if(employee == null)
{
%>
  <a href="login.jsp" class="text-center d-block mb-2">Already Registered? Click here</a>
  <%
}
  %>
 <div class="container text-center">
 <button class="btn btn-outline-success">Save</button>
 <button class="btn btn-outline-warning">Reset</button>
 </div>
</form>
<%
} else{
	%>
<form action="EmployeeServlet?action=update&empId=<%=editEmployee.getEmpId()%>" method="post">
<h3 class="text-center my-3">Edit Info</h3>
<div class="form-group">
<label for="name">Name</label>
<input type="text" value="<%=editEmployee.getEmpName() %>" class="form-control" name="empName" required="required">
    </div>
<div class="form-group">
    <label for="email">E-mail Address</label>
    <input name="empEmail" type="email" class="form-control" value="<%=editEmployee.getEmpEmail() %>" placeholder="Enter here" aria-describedby="emailInput" required="required">
  </div> 
<div class="form-group">
    <label for="password">Password</label>
    <input name="empPassword" type="password" class="form-control" value="<%=editEmployee.getEmpPassword() %>" placeholder="Enter here" aria-describedby="passwordInput">
  </div>
 <div class="form-group">
    <label for="joindate">Join Date</label>
    <input name="empJoinDate" type="date" class="form-control" value="<%=editEmployee.getEmpJoinDate() %>" placeholder="Enter here" aria-describedby="joinDateInput">
  </div>
 <div class="form-group">
    <label for="salary">Salary</label>
    <input name="empSalary"  type="number" class="form-control" value="<%=editEmployee.getEmpSalary() %>" placeholder="Enter here" aria-describedby="salarynput">
  </div>
 <div class="container text-center">
 <button class="btn btn-outline-success">Save</button>
 <button class="btn btn-outline-warning">Reset</button>
 </div>
</form>
<%
}
%>
</div>
</div>
</div>
</div>
</div>
</body>
</html>