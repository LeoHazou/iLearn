<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shop</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="iLearnCSS/iLearnCSS.css">
</head>
<body class="body">
	<!-- Refresh On Reload Script -->
	<jsp:include page="refreshOnReloadScript.jsp" />

	<!-- Start Imports -->
	<%@ page import="java.util.*"%>
	<%@ page import="java.sql.*"%>
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

		<!-- Start Shop -->
		<div class="well">
			<fieldset>
				<legend> Shop </legend>
			</fieldset>
			<%
				ArrayList<PackageBuilder> packageList = new ArrayList<PackageBuilder>();
					DBConnector conn = new DBConnector();
					PackageDAO packages = new PackageDAO();

					// Get All Package's From Table
					ResultSet rs = packages.listAll(conn);

					// Populate the ArrayList With The Packages
					try {
						while (rs.next()) {
							PackageBuilder tempPkg = new PackageBuilder(
									rs.getInt("ID"), rs.getString("Name"));

							tempPkg.setPkgDescription(rs.getString("Description"));
							tempPkg.setPkgPrice(rs.getDouble("Price"));

							packageList.add(tempPkg);
						}
					} catch (Exception e) {
						System.out.println("Error Getting All Packages!");
					}
			%>
			<table class="table table-striped">
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Actions</th>
				</tr>
				<%
					// Display All Packages
						for (PackageBuilder packageBuilder : packageList) {
				%>
				<tr>
					<td><%=packageBuilder.getPkgName()%></td>
					<td><%=packageBuilder.getPkgDescription()%></td>
					<td>$<%=packageBuilder.getPkgPrice()%></td>
					<td>
						<%
							request.setAttribute("id", packageBuilder.getPkgID());

									request.setAttribute("name", packageBuilder.getPkgName());
									request.setAttribute("description",
											packageBuilder.getPkgDescription());
									request.setAttribute("price", packageBuilder.getPkgPrice());
						%>
						<form action="AddPackageToShoppingCartServlet" method="Post">
							<input type="hidden" name="id" value="${id}"> <input
								type="hidden" name="name" value="${name}"> <input
								type="hidden" name="description" value="${description}">
							<input type="hidden" name="price" value="${price}"> <input
								type="submit" class="btn btn-primary"
								value="Add To Shopping Cart">
						</form>
					</td>
				</tr>
				<%
					}
				%>
			</table>
			<hr>
			<a href="shoppingCart.jsp"> <input type="submit"
				class="btn btn-primary" value="Proceed To Shopping Cart">
			</a>
		</div>
		<!-- End Shop -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->
	<%
		}
	%>

</body>
</html>