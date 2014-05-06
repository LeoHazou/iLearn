<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="edu.ben.cmsc398.iLearn.Model.PackageBuilder"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Questions</title>
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

		<!-- Start Edit Questions Package Form -->
		<div class="well">


			<!-- Start Message -->
			<%

				PackageBuilder pkg = (PackageBuilder) request.getSession()
							.getAttribute("pkg");
					if ((String) request.getAttribute("message") != null) {
			%>
			<div class="alert alert-success">
				<strong> <%=(String) request.getAttribute("message")%>
				</strong>
			</div>
			<%
				}
			%>
			<!-- End Message -->

			<fieldset>
				<legend> Edit Questions</legend>
			</fieldset>

			<form class="form-horizontal">

				<%
					if (pkg.getQuestions().size() == 0) {
				%>
				<table class="table table-striped" style="width: 100%">
					<tr>
						<th>This Package Has No Questions.</th>
					</tr>
				</table>
				<%
					} else {
				%>
				<%
					for (int i = 0; i < pkg.getQuestions().size(); i++) {
				%>
				<table class="table table-striped" style="width: 100%">

					<tr>
						<td align ="left" width="20%">Question</td>
						<td align ="left"><%=pkg.getQuestions().get(i).getQuestion()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%">Correct Answer</td>
						<td align ="left"><%=pkg.getQuestions().get(i).getCorrectAns()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%">First Wrong Answer</td>
						<td align ="left"><%=pkg.getQuestions().get(i).getFirstWrongAns()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%">Second Wrong Answer</td>
						<td align ="left"><%=pkg.getQuestions().get(i).getSecondWrongAns()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%">Third Wrong Answer</td>
						<td align ="left"><%=pkg.getQuestions().get(i).getThirdWrongAns()%></td>
					</tr>

					<tr>
						<td align ="left" width="20%" >Explanation</td>
						<td align ="left"><%=pkg.getQuestions().get(i).getExplanation()%></td>
					</tr>
					<tr><td align = "left" > <a href="EditPackageAddQuestions.jsp"><button type="button" class="btn btn-info">Add A Question</button></a></td>
						<td align="left"><a
							href="EditQuestions?method=POST&function=edit&ID=<%=pkg.getQuestions().get(i).getID()%>"><button
									type="button" class="btn btn-success">EDIT</button></a>
									 <a href="EditQuestions?method=POST&function=delete&ID=<%=pkg.getQuestions().get(i).getID()%>"><button
									type="button" class="btn btn-danger">DELETE</button></a>
									<a href="index.jsp"><button type="button" class="btn btn-warning">Cancel</button></a>
									<a href="editPdf.jsp"><button type="button" class="btn btn-primary">Continue</button></a>
									</td>
									
						
									
					</tr>

				</table>
				<hr>

				<%
					}
						}
				%>

			</form>
		</div>
		<!-- End Add package Form -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>
	
</body>
</html>