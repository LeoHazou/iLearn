<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<!-- Force Refresh (Even When Back Is Pressed) -->
	<input type="hidden" id="refreshed" value="no">
	<script type="text/javascript">
		onload = function() {
			var refreshed = document.getElementById("refreshed");
			if (refreshed.value == "no")
				refreshed.value = "yes";
			else {
				refreshed.value = "no";
				location.reload();
			}
		}
	</script>
</body>
</html>