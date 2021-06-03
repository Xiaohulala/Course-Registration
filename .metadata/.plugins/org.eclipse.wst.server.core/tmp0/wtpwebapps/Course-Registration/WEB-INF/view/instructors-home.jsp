<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>

	<title>List Customers</title>
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
			<h2>Course Management</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">

		
			<!-- add courses -->
			<form:form action="${pageContext.request.contextPath}/instructors/showFormForAddCourseForInstructor" 
					   modelAttribute="userName"
			           method="GET">
			           
				<input type="submit" value="Add a course" class="add-button"/>
			</form:form>
				   
			
			<!-- Search bar -->
			<form:form action="search" method="GET">
				Search Course: <input type="text" name="theSearchName"/>
				<input type="submit" value="Search" class="add-button"/>
			</form:form>
			
			<!-- add our html table here -->
			<table>
				<tr>
					<th>Course ID</a></th>
					<th>Course Name</a></th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and print our customers -->
				<c:forEach var="tempCourse" items="${courses}">
				
					<!--  construct an "delete" link with course id-->
					<c:url var="deleteLink" value="/instructors/delete">
						<c:param name="courseId" value="${tempCourse.id}"/>
					</c:url>
					
					<tr>
						<td>${tempCourse.id}</td>
						<td>${tempCourse.name}</td>
						<td>
							<a href="${deleteLink}"
							   onclick="if(!(confirm('Are you sure you want to delete this course?'))) return false">Delete</a>
						</td>
					</tr>
				</c:forEach>
			
			</table>
		</div>
	</div>

</body>
</html>