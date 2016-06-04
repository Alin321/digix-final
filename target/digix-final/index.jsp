<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
<style type="text/css">
body {
	background: url(imagini/background.jpg);
}
#header {
	height: 50px;
	width: 100%;
}
#register {
	float:left;
	width:40%;
	height:800px;
}
#welcome {
	float:left;
	width:40%;
	height:800px;
}

</style>
</head>
<body>
<div id="header">
<h1 style="width:100px; margin:auto;"> DigiX </h1>
</div>
<div id="welcome">

</div>
<div id="register">
	<form method="post" action = "/digix-final/rest/register/create" name="regForm" id="regForm">
		First Name:<br> <input type="text" name="firstName" required/> <br>
		Last Name:<br> <input type="text" name="lastName" required/> <br>
		Email:<br> <input type="email" name="email" required/> <br>
		Password:<br> <input type="password" name="password" required/> <br>
		Birthday: <br> <input type="date" name="birthday" required />
		
		<input type="submit" value="Gata" />
	</form>
	
</div >
</body>
</html>