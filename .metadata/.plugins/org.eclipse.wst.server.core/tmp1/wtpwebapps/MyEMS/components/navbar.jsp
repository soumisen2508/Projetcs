<%@ page import="myems.entities.Employee" %>
<%
Employee employee1=(Employee)session.getAttribute("current-employee");
%>

<nav class="navbar navbar-expand-lg navbar-dark custom-bg">
<div class="container">
<a class="navbar-brand" href="#!">Employee Management System</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
    <%
    if(employee1==null){
    	%>
    	<li class="nav-item active">
        <a class="nav-link" href="login.jsp">Login</a>
      </li>
    	<%
    }
    
    else if(employee1.getEmpType().equals("normal")){
    	%>
    	<li class="nav-item active">
        <a class="nav-link" href="LogoutServlet">Logout</a>
      </li>
      <%
    }
    else{
    	%>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
         Menu
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="EmployeeServlet?action=new">Add Employee</a>
          <a class="dropdown-item" href="search.jsp">Search Employee</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="EmployeeServlet?action=payslip">Generate Payslip</a>
        </div>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="LogoutServlet">Logout</a>
      </li>
    	<%
    	
    }
    %>
 
      </ul>
      </div>
</div>
</nav>