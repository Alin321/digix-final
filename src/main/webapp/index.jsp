<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>

</head>
<body>
<h1> TEXT ACILEA </h1>
<br>
	<form method="post" action = "http://localhost:8082/digix-final/rest/register/create" name="regForm" id="regForm">
		First Name: <input type="text" name="firstName" />
		Last Name: <input type="text" name="lastName" />
		Email: <input type="email" name="email" />
		Password: <input type="password" name="password" />
		<input type="submit" value="Gata" />
	</form>
	
	
</body>
</html>