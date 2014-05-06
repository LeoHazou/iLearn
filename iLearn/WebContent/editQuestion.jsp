<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="java.util.ArrayList"%>
<%@page import="edu.ben.cmsc398.iLearn.Model.Question"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Question</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">
	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Start Imports -->
	<%@ page import="edu.ben.cmsc398.iLearn.Model.*"%>
	<!-- End Imports -->

	<!-- Start User Verification -->
	<%
		// Initialize A User Object To Null
		User user = null;

		// Initialize A Boolean Variable To False
		boolean verified = false;

		// If A Session Exists and A User Object Is In The Session,
		// Change The User Object
		// To The One Retrieved From The Session
		if (session != null && session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
			verified = true;
		} else {
			response.sendRedirect("login.jsp");
		}
	%>
	<!-- End User Verification -->

	<!-- Static Top Bar -->
	<jsp:include page="topBar.jsp" />


	<%
		// If The User Is Verified Display The My Profile Page
		if (verified == true) {
	%>
	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />

		<!-- Start Add Questions Form -->
		<div class="well">
			<fieldset>
				<legend> Edit Question </legend>
			</fieldset>

			<!-- Start Message -->
			<%
				if (request.getParameter("message") != null) {
			%>
			<div class="alert alert-error">
				<strong> <%=request.getParameter("message")%>
				</strong>
			</div>
			<%
				}
			%>
			<!-- End Message -->

				<% // Get attributes
				Question questions = (Question) request.getSession().getAttribute("question");
				;%>
				
				
			<form class="form-horizontal" name="EditQuestionsForm"
				action="EditQuestions" method="POST">
				<div class="control-group">
					<label class="control-label">Question</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								value="<%=questions.getQuestion() %>" name="question" required>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Correct Answer</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								value="<%=questions.getCorrectAns() %>" name="correctAns"
								required>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">First Wrong Answer</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								value="<%=questions.getFirstWrongAns() %>" name="firstWrongAns"
								required>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Second Wrong Answer</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								value="<%=questions.getSecondWrongAns() %>"
								name="secondWrongAns" required>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Third Wrong Answer</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								value="<%=questions.getThirdWrongAns() %>" name="thirdWrongAns"
								required>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Answer Explanation</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								value="<%=questions.getExplanation() %>"
								name="explanation" required>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
				<input type="submit" class="btn btn-primary"
							value="Save" name = "function">			 
				<a href="editQuestionProperties.jsp"><button type="button" class="btn btn-primary">Cancel</button></a>
					</div>
					</div>
			</form>
		</div>
		<!-- End Add Questions Form -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>

</body>
</html>