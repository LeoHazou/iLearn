<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">

<!-- Start Drop Down Menu Script -->
<style>
ul.nav li.dropdown:hover>ul.dropdown-menu {
	display: block;
}
</style>
<!-- End Drop Down Menu Script -->

</head>
<body>
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
		// Change The Verified Boolean Value To True, And Change The User Object
		// To The One Retrieved From The Session
		if (session != null && session.getAttribute("user") != null) {
			user = (User) session.getAttribute("user");
			verified = true;
		}
	%>
	<!-- End User Verification -->

	<!-- Start Page Header -->
	<div class="header text-center">

		<!-- Start Navigation Bar -->
		<div class="navbar">
			<div class="navbar-inner">
				<a class="brand" href="index.jsp"> iLearn </a>

				<!-- Start Navigation Bar Elements -->
				<%
					// If The User Is Verified 
					// Display The Appropriate Navigation Bar Elements 
					// For The User Type
					if (verified) {
						if (user.getType().equals("User")) {
				%>
				<!--  User Navigation Bar Elements -->
				<ul class="nav pull-left">
					<li><a href="myProfile.jsp"> My Profile </a></li>
					<li><a href="myPackages.jsp"> My Packages </a></li>
				</ul>
				<ul class="nav pull-right">
					<li><a href="shop.jsp"> Shop </a></li>
					<li><a href="shoppingCart.jsp"> Shopping Cart </a></li>
				</ul>
				<%
					} else if (user.getType().equals("Administrator")) {
				%>
				<!--  Administrator Navigation Bar Elements -->
				<ul class="nav pull-left">
					<li><a href="myProfile.jsp"> My Profile </a></li>
				</ul>

				<ul class="nav pull-right">
				
					<!-- Start Manage Packages Drop Down Menu -->
					<li class="dropdown"><a href="">Manage Packages</a>
						<ul class="dropdown-menu">
							<li><a href="addPackage.jsp"> Add New Packages </a></li>
							<li><a href="editPackage.jsp"> Edit Existing Packages </a></li>
						</ul></li>
					<!-- End Manage Packages Drop Down Menu -->
				</ul>

				<%
					}
						if (user.getType().equals("SuperAdministrator")) {
				%>
				<!--  Super Administrator Navigation Bar Elements -->
				<ul class="nav pull-left">
					<li><a href="myProfile.jsp"> My Profile </a></li>
				</ul>

				<ul class="nav pull-right">
				
					<!-- Start Manage Packages Drop Down Menu -->
					<li class="dropdown"><a href="">Manage Packages</a>
						<ul class="dropdown-menu">
							<li><a href="addPackage.jsp"> Add New Packages </a></li>
							<li><a href="editPackage.jsp"> Edit Existing Packages </a></li>
						</ul></li>
					<!-- End Manage Packages Drop Down Menu -->
					
					<!-- Start Manage Users Drop Down Menu -->
					<li class="dropdown"><a href="">Manage Users</a>
						<ul class="dropdown-menu">
							<li><a href="manageUsers.jsp"> Manage User Accounts </a></li>
							<li><a href="ManageAdmins?method=POST&function=view"> Manage Administrator Accounts </a></li>
						</ul></li>
					<!-- End Manage Users Drop Down Menu -->
				</ul>

				<%
					}
					}
				%>
				<!-- End Navigation Bar Elements -->
			</div>
		</div>
		<!-- End Navigation Bar -->

	</div>
	<!-- End Page Header -->

</body>
</html>