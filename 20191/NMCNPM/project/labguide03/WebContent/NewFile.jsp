<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<sql:query var="students" dataSource="jdbc/ClassManagement">
select * from Student;
</sql:query>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users List</title>
</head>
<body>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>List of students</h2>
			</caption>
			<tr>
				<th>StudentId</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Address</th>
				<th>Telephone</th>
				<th>Email</th>
				<th>Password</th>
			</tr>
			<c:forEach var="s" items="${students.rows}">
				<tr>
					<td><c:out value="${s.StudentId}" /></td>
					<td><c:out value="${s.FirstName}" /></td>
					<td><c:out value="${s.LastName}" /></td>
					<td><c:out value="${s.Address}" /></td>
					<td><c:out value="${s.Telephone}" /></td>
					<td><c:out value="${s.Email}" /></td>
					<td><c:out value="${s.Password}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>