<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title>Add Course</title>
	
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css"/>
		  
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css"/>

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>Course Management</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Save Course</h3>
		
		<form:form action="saveCourse" modelAttribute="course" method="POST">
			<table>
				<tbody>
					<tr>
						<td><label>Course Name:</label></td>
						<td><form:input path="name"/></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class=/></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		
		<div style="clear;both"></div>
		<p>
			<a href="${pageContext.request.contextPath}/instructors/">Back to Home</a>
		</p>
		
	</div>
	
</body>
</html>