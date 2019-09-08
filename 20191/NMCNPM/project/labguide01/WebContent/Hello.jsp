<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello!</h1>
	<h3>your username: <%= request.getParameter("username") %></h3>
	<h3>your user-mail: <%= request.getParameter("user-email") %></h3>
</body>
</html>