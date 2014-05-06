<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Questions</title>
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
			if (user.getType().equals("Administrator") || user.getType().equals("SuperAdministrator"))
			{
				verified = true;
			}
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
				<legend> Add Questions </legend>
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
			
			<!-- Start Success Message -->
			<%
				if (request.getParameter("successMessage") != null) {
			%>
			<div class="alert alert-success">
				<strong> <%=request.getParameter("successMessage")%>
				</strong>
			</div>
			<%
				}
			%>
			<!-- End Success Message -->

			<form class="form-horizontal" name="AddQuestionForm"
				action="AddQuestion" method="POST">
				<div class="control-group">
					<label class="control-label">Question</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								placeholder="Type question here" name="question" required <% if ( request.getParameter("question") != null){ %> value="<%=request.getParameter("question") %>" <% } %>>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">Correct Answer</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								placeholder="Type correct answer here" name="correctAns" <% if ( request.getParameter("correctAns") != null){ %> value="<%=request.getParameter("correctAns") %>" <% } %>
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
								placeholder="Type first wrong answer here" name="firstWrongAns" <% if ( request.getParameter("firstWrongAns") != null){ %> value="<%=request.getParameter("firstWrongAns") %>" <% } %>
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
								placeholder="Type second wrong answer here" <% if ( request.getParameter("secondWrongAns") != null){ %> value="<%=request.getParameter("secondWrongAns") %>" <% } %>
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
								placeholder="Type third wrong answer here" name="thirdWrongAns" <% if ( request.getParameter("thirdWrongAns") != null){ %> value="<%=request.getParameter("thirdWrongAns") %>" <% } %>
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
								placeholder="Type explanation to the answer here" <% if ( request.getParameter("explanation") != null){ %> value="<%=request.getParameter("explanation") %>" <% } %>
								name="explanation" required>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="submit" class="btn btn-success"
							value="Save And Add More Questions" name="value"> <input
							type="submit" class="btn btn-primary"
							value="Save And Return" name="value">
							<a href="editQuestionProperties.jsp"><button type="button" class="btn btn-danger">Cancel and Return</button></a>
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