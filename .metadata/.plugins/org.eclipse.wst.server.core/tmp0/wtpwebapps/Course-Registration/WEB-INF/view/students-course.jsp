<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>

	<title>My Course Management</title>
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
			<h2>My Course Management</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			
			<!-- Search bar -->
			<form:form action="${pageContext.request.contextPath}/students/mySearch" method="GET">
				Search Course: <input type="text" name="theSearchName"/>
				<input type="submit" value="Search" class="add-button"/>
			</form:form>
			
			<!-- add our html table here -->
			<table>
				<tr>
					<th>Course ID</a></th>
					<th>Course Name</a></th>
					<th>Course Rating</a></th>
					<th>Course Instructor</a></th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempCourse" items="${courses}">
				
					<!--  construct a "dropout" link with course id-->
					<c:url var="dropoutLink" value="/students/dropout">
						<c:param name="courseId" value="${tempCourse.id}"/>
					</c:url>
					
					<!--  construct a "rate" link -->
					<c:url var="rateLink" value="/students/rate">
						<c:param name="courseId" value="${tempCourse.id}"/>
					</c:url>
					
					<!--  construct a "review" link -->
					<c:url var="reviewLink" value="/students/reviews">
						<c:param name="courseId" value="${tempCourse.id}"/>
					</c:url>
					<tr>
						<td>${tempCourse.id}</td>
						<td>${tempCourse.name}</td>
						<c:if test="${tempCourse.rating == -1.0}">
							<td>
							<a href="${rateLink}">Be the first to rate!</a>
							</td>
						</c:if>
						<c:if test="${tempCourse.rating != -1.0}">
							<td>
							<a href="${reviewLink}">${tempCourse.rating}</a>
							</td>
						</c:if>
						<td>${tempCourse.instructor}</td>
						<td>
							<a href="${dropoutLink}"
							   onclick="if(!(confirm('Are you sure you want to drop out this course?'))) return false">Drop out</a>
						</td>
					</tr>
				</c:forEach>
			
			</table>
		</div>
	</div>
	<br>
	<a href="${pageContext.request.contextPath}/students/">Back to Home Page</a>

</body>
</html>