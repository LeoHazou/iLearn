<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>
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

		<!-- Start Checkout -->
		<div class="well">
			<fieldset>
				<legend> Checkout </legend>
			</fieldset>

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

			<%
				float totalPrice = 0;

					// If The User's Shopping Cart Is Not Empty, Display Items, Otherwise Redirect User
					if (!user.getShoppingCartPackageList().isEmpty()) {
			%>
			<table class="table table-striped">
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
				</tr>
				<%
					for (PackageBuilder packageBuilder : user
									.getShoppingCartPackageList()) {
				%>
				<tr>
					<td><%=packageBuilder.getPkgName()%></td>
					<td><%=packageBuilder.getPkgDescription()%></td>
					<td>$<%=packageBuilder.getPkgPrice()%></td>
				</tr>
				<%
					totalPrice += packageBuilder.getPkgPrice();
							}
				%>
				<tr>
					<td></td>
					<td></td>
					<th>Total</th>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>$<%=totalPrice%></td>
				</tr>
			</table>
			<hr>

			<form action="CheckoutServlet" method="Post">
				<a href="shoppingCart.jsp"> <input type="submit"
					class="btn btn-primary" value="Back To Shopping Cart">
				</a> <input type="submit" class="btn btn-primary" value="Checkout">
			</form>
			<%
				} else {
						String message = "Shopping Cart Is Empty!";
						response.sendRedirect("shoppingCart.jsp?message=" + message);
					}
			%>
		</div>
		<!-- End Checkout -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>

</body>
</html>