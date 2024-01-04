<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>First Registration For Shopping Cart</title>
</head>
<body>
<center><h1><font color="blue" size="10px">Register</font></h1></center>
<form action="../RegServlet" method="post">
<table style="width:600px" align="center" border="1px" cellspacing="10px" >
<tr style="height:30px">
<td style="width:300px"><font color="blue">Name:</font></td>
<td>
<input type="text" name="userName" style="width:300px; height:20px">
</td>
</tr>
<tr style="height:30px">
<td style="width:300px"><font color="blue">Birthday:</font></td>
<td>
<input type="text" name="userBirth" style="width:300px; height:20px">
</td>
</tr>
<tr style="height:30px">
<td style="width:300px"><font color="blue">Address:</font></td>
<td>
<input type="text" name="userAdd" style="width:300px; height:20px">
</td>
</tr>
<tr style="height:30px" >
<td style="width:300px"><font color="blue">Contact No:</font></td>
<td>
<input type="text" name="userTel" style="width:300px; height:20px">
</td>
</tr>
<tr style="height:30px">
<td style="width:300px"><font color="blue">E-mail Address:</font></td>
<td>
<input type="text" name="userMail" style="width:300px; height:20px">
</td>
</tr>
<tr style="height:30px">
<td colspan="2" align="center">
<input type="submit" value="SUBMIT" style="width:240px; height:30px; color:blue"  />
</td>
</tr>
</table>
</form>
</body>
</html>