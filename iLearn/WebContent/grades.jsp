<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="edu.ben.cmsc398.iLearn.Dao.*"%>
<%@ page import="edu.ben.cmsc398.iLearn.Model.*"%>
<!DOCTYPE html>
<html>
<head>


<style>
table.hidden {
	
	width: 87%;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="Graph/basic.css" type="text/css" rel="stylesheet" />
<link href="Graph/visualize.css" type="text/css" rel="stylesheet" />
<link href="Graph/visualize-light.css" type="text/css" rel="stylesheet" />
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="Graph/visualize.js"></script>
<title>Grades</title>

<script type="text/javascript">
	$(function() {
		$('table').visualize({
			type : 'bar',
			colors : [ '#003EFF' ],
			barMargin : 100
		});
	});
</script>
</head>
<body class="body">



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

	

	<%
		// If The User Is Verified Display The My Profile Page
		if (verified == true) {
	%>


	
		

	
		

			<%
				if (request.getParameter("size") != null
							&& request.getParameter("grade1") != null
							&& request.getParameter("grade2") != null
							&& request.getParameter("grade3") != null
							&& request.getParameter("grade4") != null
							&& request.getParameter("grade5") != null
							&& request.getParameter("grade6") != null) {
						String count = request.getParameter("size");
						int size = Integer.parseInt(count);
						float grade1 = Float.parseFloat(request
								.getParameter("grade1"));
						float grade2 = Float.parseFloat(request
								.getParameter("grade2"));
						float grade3 = Float.parseFloat(request
								.getParameter("grade3"));
						float grade4 = Float.parseFloat(request
								.getParameter("grade4"));
						float grade5 = Float.parseFloat(request
								.getParameter("grade5"));
						float grade6 = Float.parseFloat(request
								.getParameter("grade6"));
			%>

			<table class="hidden" align="center">
				<caption>Progress Report</caption>
				<thead>
					<tr>
						<td></td>
						<%
							for (int k = 1; k <= size; k++) {
						%>
						<th scope="col">Test <%=k%></th>
						<%
							}
						%>

					</tr>
				</thead>
				<tbody>

					<tr>
						<th scope="row">Student Name: <%=user.getFirstName() + " " + user.getLastName()%></th>
						<%
							if (grade1 > -1) {
						%>
						<td><%=grade1%></td>
						<%
							}
						%>
						<%
							if (grade2 > -1) {
						%>
						<td><%=grade2%></td>
						<%
							}
						%>

						<%
							if (grade3 > -1) {
						%>
						<td><%=grade3%></td>
						<%
							}
						%>

						<%
							if (grade4 > -1) {
						%>

						<td><%=grade4%></td>
						<%
							}
						%>
						<%
							if (grade5 > -1) {
						%>
						<td><%=grade5%></td>
						<%
							}
						%>
						<%
							if (grade6 > -1) {
						%>
						<td><%=grade6%></td>
						<%
							}
						%>



					</tr>

				</tbody>
			</table>

			<%
				} else {
						response.sendRedirect("myPackages.jsp");
					}
			%>

		
	
		
	
	<%
		}
	%>
	


</body>
</html>