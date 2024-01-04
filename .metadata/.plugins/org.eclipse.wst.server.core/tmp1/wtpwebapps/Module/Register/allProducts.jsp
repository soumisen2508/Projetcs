<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="soumi.zust.iteeio.model.ProductModel" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
</head>
<body>
<%
List<ProductModel>productList = (List<ProductModel>)session.getAttribute("typeProduct");
for(ProductModel pm:productList){
	%>
	<table width="100%" border="0" cellspacing="0" cellpadding="1">
	<tr>
	<td colspan="3"><img src="/Img/<%=pm.getDefaultImg()%>" width="80px" height="80px"/></td>
	</tr>
	<tr>
	<td><%= pm.getProductTitle() %></td>
	</tr>
	<tr>
	<td><%= pm.getProductPrice() %></td>
	</tr>
	<tr>
	<td><%= pm.getShortDesc() %></td>
	</tr>
	</table>
	<%
	}
%>
</body>
</html>