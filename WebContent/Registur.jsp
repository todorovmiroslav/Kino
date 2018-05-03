<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body>
	
	<form action="RegisturSurvlet" method="post">
		First Name: <input type="text" name="firstname" placeholder="Enter your first name" required> <br> <br>
		Last Name: <input type="text" name="lastname" placeholder="Enter your last name" required> <br> <br>
		Username: <input type="text" name="username" placeholder="Enter your username" required> <br> <br>
		Password: <input type="password" name="password" placeholder="Enter your password" required> <br> <br>
		e-mail: <input type="text" name="email" placeholder="Enter your e-mail" required> <br> <br>
		Phone Number: <input type="text" name="phone" placeholder="Enter your phone number" required maxlength="10"> <br> <br>
		
		<a href="http://localhost:8080/kinobotevgrad.bg/"><button>Registur</button> </a>
	</form>
</body>
</html>