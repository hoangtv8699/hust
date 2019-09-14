<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="models.UserBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		UserBean bean=(UserBean)request.getAttribute("bean");
		out.print("Welcome to new user! " + bean.getFirstname() + " " + bean.getLastname() + "<br>");
		out.print("Username: " + bean.getUsername()+ "<br>");
		out.print("Password: " + bean.getPassword()+ "<br>");
	%>
</body>
</html>