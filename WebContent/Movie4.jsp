<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Movie4</title>
	</head>
	<body>
		<p>Movie4</p>
		<div>
			<img border="23%" src="images/4.jpg" alt="avengers1" width="200" height="300" align="middle" >
		</div>
	
		<% if(request.getSession().getAttribute("USER") != null){ %>
		<p align="center" ><a href="http://localhost:8080/kinobotevgrad.bg/LoginSurvlet" > <u> return </u> </a></p>
		<% }else{ %>
		<p align="center" ><a href="http://localhost:8080/kinobotevgrad.bg/MainPage.jsp" > <u> return </u> </a></p>
		<% } %>
		
	</body>
</html>