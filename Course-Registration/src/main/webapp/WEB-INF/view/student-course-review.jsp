<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>

	<title>Course Reviews</title>
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout"/>
	</form:form>
	
	<div id="wrapper">
		<div id="header">
			<h2>${course.name} Reviews</h2>
		</div>
	</div>
	<div id="container">
		<div id="content">
			<!-- add courses -->
			<c:url var="submitReview" value="/students/rate">
				<c:param name="courseId" value="${courseId}"/>
			</c:url>
			<a href="${submitReview}">Submit a Review</a>
			
			
			<!-- add our html table here -->
			<table>
				<tr>
					<th>Rating</a></th>
					<th>Review</a></th>
					<th>Student</a></th>

				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempReview" items="${reviews}">
					
					<tr>
						<td>${tempReview.rating}</td>
						<td>${tempReview.comment}</td>
						<td>${tempReview.student.firstName} ${tempReview.student.lastName}</td>
					</tr>
				</c:forEach>
			
			</table>
		</div>
	</div>
		<c:url var="back" value="/students/myCourse">
		<c:param name="courseId" value="${courseId}"/>
	</c:url>
	
	<br>
	<a href="${back}">Back to My Course</a>
</body>
</html>