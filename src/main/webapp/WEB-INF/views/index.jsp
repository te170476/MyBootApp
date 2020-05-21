<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	<h1>Index Page</h1>
	<p>this is sample page for Spring Boot!</p>
	<p>${msg}</p>
	<form method="post" action="/post">
		<input type="text" name="text1"><input type="submit">
	</form>
</body>
</html>