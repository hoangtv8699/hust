<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="RegisterServlet" method="post">
		Username:<br>
		 <input type="text" name="user"> <br>
		Password:<br>
		 <input type="password" name="pwd"> <br>
		Nhap lai Password:<br>
		<input type="password" name="repwd"> <br>
		 <input type="submit" value="Register">
	</form>
</body>
</html>