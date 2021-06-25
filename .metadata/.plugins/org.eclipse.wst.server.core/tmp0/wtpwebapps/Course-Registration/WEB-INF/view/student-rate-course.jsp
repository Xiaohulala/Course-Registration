<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!doctype html>
<html lang="en">

<head>
	
	<title>Register New User Form</title>
	
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
		<style>
		.error {color:red}
	</style>

</head>

<body>

	<div>
		
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
			
			<div class="panel panel-primary">

				<div class="panel-heading">
					<div class="panel-title">Rate the Course</div>
				</div>

				<div style="padding-top: 20px" class="panel-body">

					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/students/processReview" 
						  	   modelAttribute="review"
						  	   class="form-horizontal">

					    <!-- Place for messages: error, alert etc ... -->
					    <div class="form-group">
					        <div class="col-xs-15">
					            <div>
								
									<!-- Check for registration error -->
									<c:if test="${reviewError != null}">
										<script type="text/javascript">
										    alert("You already submitted a review");
										</script> 
									</c:if>
																			
					            </div>
					        </div>
					    </div>

						<!-- role -->
						<div style="margin-bottom: 25px" class="input-group">
							<form:errors path="rating" cssClass="error" />
							*How much would you give this course?
							<br>
							<form:radiobutton path="rating" value="1.0" /> 1   &nbsp &nbsp
							<form:radiobutton path="rating" value="2.0"/> 2 &nbsp &nbsp
							<form:radiobutton path="rating" value="3.0"/> 3 &nbsp &nbsp
							<form:radiobutton path="rating" value="4.0"/> 4 &nbsp &nbsp
							<form:radiobutton path="rating" value="5.0"/> 5 &nbsp &nbsp
							
						</div>
						
						<!-- Comment -->
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:errors path="comment" cssClass="error" />
							<form:input path="comment" placeholder="Write your review for the course here." class="form-control" />
						</div>
						<form:hidden path="courseId" value="${courseId}"/>
						<!-- Register Button -->
						<div style="margin-top: 10px" class="form-group">						
							<div class="col-sm-6 controls">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>
						
					</form:form>

				</div>

			</div>

		</div>

	</div>
	
	
	

	
	<c:url var="back" value="/students/reviews">
		<c:param name="courseId" value="${courseId}"/>
	</c:url>
	
	<br>
	<a href="${back}">Back to Course Review</a>
</body>
</html>