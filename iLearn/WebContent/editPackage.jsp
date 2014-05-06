<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Package</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">
	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Start User Verification -->
	<%
		// If A Session Exists And A User Object Is In The Session,
		// Redirect The User To The Home JSP Page
// 		if (session != null && session.getAttribute("user") != null) {
// 			response.sendRedirect("index.jsp");
// 		}
	%>
	<!-- End User Verification -->

	<!-- Static Top Bar -->
	<jsp:include page="topBar.jsp" />

	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />

		<!-- Start Edit Package Form -->
		<div class="well">
			<fieldset>
				<legend> Edit Package </legend>
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

			<form class="form-horizontal" name="EditackageForm" action="EditPackage"  method="POST">
				<div class="control-group">
					<label class="control-label">Package ID number</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge" placeholder="Package ID number"
								name="pkgID" required>
						</div>
					</div>
				</div>
				
				
				
				
				
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
						<input type="submit" class="btn btn-primary" value="Start Editing Package">
					</div>
				</div>
				
				
			</form>
		</div>
		<!-- End Edit package Form -->
		
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
					<form name ="list" action="ListAll" method="POST" target="_blank">
						<input type="submit" class="btn btn-primary" value="View Package ID List">
						</form>
					</div>
				</div>
		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
</body>

</html>