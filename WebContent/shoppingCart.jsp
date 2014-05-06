<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shopping Cart</title>
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

		<!-- Start Shopping Cart -->
		<div class="well">
			<fieldset>
				<legend> Shopping Cart </legend>
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
				// If The User's Shopping Cart Is Not Empty, Display Items, Otherwise Notify User
					if (!user.getShoppingCartPackageList().isEmpty()) {
			%>
			<table class="table table-striped">
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Actions</th>
				</tr>
				<%
					for (PackageBuilder packageBuilder : user
									.getShoppingCartPackageList()) {
				%>
				<tr>
					<td><%=packageBuilder.getPkgName()%></td>
					<td><%=packageBuilder.getPkgDescription()%></td>
					<td>$<%=packageBuilder.getPkgPrice()%></td>
					<td>
						<%
							request.setAttribute("id", packageBuilder.getPkgID());

										request.setAttribute("name",
												packageBuilder.getPkgName());
										request.setAttribute("description",
												packageBuilder.getPkgDescription());
										request.setAttribute("price",
												packageBuilder.getPkgPrice());
						%>

						<form action="RemovePackageFromShoppingCartServlet" method="Post">
							<input type="hidden" name="id" value="${id}"> <input
								type="hidden" name="name" value="${name}"> <input
								type="hidden" name="description" value="${description}">
							<input type="hidden" name="price" value="${price}"> <input
								type="submit" class="btn btn-danger"
								value="Remove From Shopping Cart">
						</form>
					</td>
				</tr>
				<%
					}
				%>
			</table>
			<hr>
			<a href="shop.jsp"> <input type="submit" class="btn btn-primary"
				value="Back To Shop">
			</a> <a href="checkout.jsp"> <input type="submit"
				class="btn btn-primary" value="Proceed To Checkout">
			</a>
			<%
				} else {
			%>
			<h5>Your Shopping Cart is Empty!</h5>
			<hr>
			<a href="shop.jsp"> <input type="submit" class="btn btn-primary"
				value="Back To Shop">
			</a>
			<%
				}
			%>

		</div>
		<!-- End Shopping Cart -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>

</body>
</html>