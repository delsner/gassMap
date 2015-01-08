<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>MapPage</title>
	</head>
	<body>
		<sec:ifLoggedIn> 
			<p>Hallo ${per.username} ! ${address.street}</p>
		</sec:ifLoggedIn>
		<sec:ifNotLoggedIn>
			<p>Hier gibt's nichts zu sehen!</p>
		</sec:ifNotLoggedIn>
	</body>
</html>
