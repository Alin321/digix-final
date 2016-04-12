<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1> TEXT ACILEA </h1>
<br>
	<form method="post" action="http://localhost:8082/digix-final/rest/register/create" name="registerForm" id="regForm">
		First Name: <input type="text" name="firstName" />
		Last Name: <input type="text" name="lastName" />
		Email: <input type="email" name="email" />
		Password: <input type="password" name="password" />
		<input type="submit" value="Gata" />
	</form>
	<script type="text/javascript">
		function submit() {
			var formData = JSON.stringify($("#regForm").serializeArray());
			$.ajax({
				  type: "POST",
				  url: "http://localhost:8082/digix-final/rest/register/create",
				  data: formData,
				  success: function(){},
				  dataType: "json",
				  contentType : "application/json"
				});
		}
		
	
	</script>
</body>
</html>