<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>ManagePage</title>
	</head>
	<body>
		<div>
		<sec:ifAnyGranted roles="ROLE_ADMIN">
		Hallo Herr Admin
		</sec:ifAnyGranted>
		
		<sec:ifAnyGranted roles="ROLE_USER">
		Hallo Herr User
		</sec:ifAnyGranted>
		</div>
	</body>
</html>