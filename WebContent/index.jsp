<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
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

		<!-- Start Home Page -->
		<div class="well">

			<!-- Start Carousel -->
			<div id="myCarousel" class="carousel slide">
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<!-- Carousel items -->
				<div class="carousel-inner">
					<div class="active item">
						<img src="img/Placeholder1.png" width="100%">
						<div class="container"></div>
					</div>
					<div class="item">
						<img src="img/Placeholder2.png" width="100%">
						<div class="container"></div>
					</div>
					<div class="item">
						<img src="img/Placeholder3.png" width="100%">
						<div class="container"></div>
					</div>
				</div>
				<!-- Carousel nav -->
				<a class="carousel-control left" href="#myCarousel"
					data-slide="prev">&lsaquo;</a> <a class="carousel-control right"
					href="#myCarousel" data-slide="next">&rsaquo;</a>
			</div>
			<!-- End Carousel -->

		</div>
		<!-- End Home Page -->

		<!-- Static Footer -->
		<jsp:include page="footer.jsp" />
	</div>
	<!-- End Page Container -->

	<!-- Start Java Script -->
	<script src="http://code.jquery.com/jquery.min.js"></script>
	<script src="bootstrap/js/bootstrap.js"></script>
	<!-- End Java Script -->
</body>
</html>