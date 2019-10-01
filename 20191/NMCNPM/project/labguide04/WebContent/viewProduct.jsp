<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<th>StudentId</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>Address</th>
				<th>Telephone</th>
				<th>Email</th>
				<th>Password</th>
		</tr>
		<c:forEach var="student" items="${StudentList }">
			<tr>
				<td>${student.studentID}</td>
				<td>${student.firstname}</td>
				<td>${student.lastname}</td>
				<td>${student.telephone}</td>
				<td>${student.email}</td>
				<td>${student.address}</td>
				<td>${student.password}</td>
			</tr>
		</c:forEach>
	</table>


	<c:if test="${currentPage != 1 }">
		<td><a href="product.do?page=${currentPage - 1 }">Previous</a></td>
	</c:if>

	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${noOfPages }" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td><a href="product.do?page=${i}">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>

	<c:if test="${currentPage lt noOfPages }">
		<td><a href="product.do?page=${currentPage + 1 }">Next</a></td>
	</c:if>
</body>
</html>