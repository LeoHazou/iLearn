<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Users</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">
	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Static Top Bar -->
	<jsp:include page="topBar.jsp" />

	<!-- Start Page Container -->
	<div class="container" style="background-color: white; padding: 10px;">
		<!-- Static Header -->
		<jsp:include page="header.jsp" />
		
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

		<!-- Start About Us -->
		<div class="well">
			<fieldset>
				<legend> Search User By Email: </legend>
			</fieldset>
			<form class="form-horizontal" name="SeachUserForm" action="ManageUser" method="POST">
				<div class="control-group">
					<label class="control-label">Email</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="email" class="input-xlarge"
								value="" name="email" required placeholder="Type Email Here">
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
				<input type="submit" class="btn btn-primary"
							value="Search Email" name = "function">			 
					</div>
				</div>
			</form>
			<fieldset>
				<legend> Search User By Last Name: </legend>
			</fieldset>
			
						<form class="form-horizontal" name="SeachUserForm" action="ManageUser" method="POST">
				<div class="control-group">
					<label class="control-label">Last Name</label>
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on"><i class="icon-hand-right"></i></span> <input
								type="text" class="input-xlarge"
								value="" name="lastName" required placeholder="Type Last Name Here">
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label"></label>
					<div class="controls">
				<input type="submit" class="btn btn-primary"
							value="Search Last Name" name = "function">			 
					</div>
				</div>
			</form>
		
		</div>
		<!-- End About Us -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
</body>
</html>