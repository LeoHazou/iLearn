<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quiz</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">
	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Start Imports -->
	<%@ page import="java.util.*"%>
	<%@ page import="edu.ben.cmsc398.iLearn.Dao.*"%>
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
			if (user.getType().equals("User")) {
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

		<!-- Start Quiz -->
		<div class="well">
			<fieldset>
				<legend> Quiz </legend>
			</fieldset>

			<%
				// If The Package ID RequestParam Exists
					// If The User Owns The Package In The RequestParam
					// Populate The Package Object With The Appropriate Questions
					if (request.getParameter("packageID") != null) {
						PackageDAO packageDAO = new PackageDAO();
						DBConnector conn = new DBConnector();
						if (packageDAO
								.userHasPackage(user.getiD(),
										Integer.parseInt(request
												.getParameter("packageID")), conn)) {
							
							
							// Get Package And Randomize Questions
							PackageBuilder packageBuilder = packageDAO.getPkg(
									Integer.parseInt(request
											.getParameter("packageID")), conn);
							QuestiondDAO questionDAO = new QuestiondDAO();
							packageBuilder.setQuestions(questionDAO.getQuestions(
									Integer.parseInt(request
											.getParameter("packageID")), conn));
							packageBuilder.randomizeQuestions();
			%>
			<form action="GradeQuiz" method="Post">
				<input type="hidden" name="packageID"
					value=<%=request.getParameter("packageID")%>> <input
					type="hidden" name="numberOfQuestions"
					value=<%=packageBuilder.getQuestions().size()%>>

				<table class="table table-striped">
					<%
						for (int i = 0; i < packageBuilder.getQuestions()
											.size(); i++) {
										ArrayList<String> list = new ArrayList<String>();
										list.add(packageBuilder.getQuestions().get(i)
												.getFirstWrongAns());
										list.add(packageBuilder.getQuestions().get(i)
												.getSecondWrongAns());
										list.add(packageBuilder.getQuestions().get(i)
												.getThirdWrongAns());
										list.add(packageBuilder.getQuestions().get(i)
												.getCorrectAns());
										Collections.shuffle(list);
					%>
					<tr>
						<th><%=(i + 1) + ")"%> <%=packageBuilder.getQuestions().get(i)
									.getQuestion()%></th>
					</tr>
					<tr>
						<td><input type="radio" name=<%=i%>
							value=<%=list.get(0).equals(
									packageBuilder.getQuestions().get(i)
											.getCorrectAns())%>>
							<%=list.get(0)%></td>
					</tr>
					<tr>
						<td><input type="radio" name=<%=i%>
							value=<%=list.get(1).equals(
									packageBuilder.getQuestions().get(i)
											.getCorrectAns())%>>
							<%=list.get(1)%></td>
					</tr>
					<tr>
						<td><input type="radio" name=<%=i%>
							value=<%=list.get(2).equals(
									packageBuilder.getQuestions().get(i)
											.getCorrectAns())%>>
							<%=list.get(2)%></td>
					</tr>
					<tr>
						<td><input type="radio" name=<%=i%>
							value=<%=list.get(3).equals(
									packageBuilder.getQuestions().get(i)
											.getCorrectAns())%>>
							<%=list.get(3)%></td>
					</tr>
					<%
						}
					%>
				</table>
				<hr>
				<input type="submit" class="btn btn-primary"
					value="Submit For Grading">
			</form>
		</div>
		<!-- End Quiz -->
		<%
			conn.closeConnection();
					} else {
		%>
		<div class="alert alert-error">
			<strong> You Do Not Own This Package! </strong>
		</div>
		<%
			}
				} else {
		%>
		<div class="alert alert-error">
			<strong> No Package Specified! </strong>
		</div>
		<%
			}
		%>
		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>
</body>
</html>