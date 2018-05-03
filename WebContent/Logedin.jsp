<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>KinoBotevgrad</title>
</head>
<body>
	<p align="center">
		Welcome,
		<%=request.getSession().getAttribute("USER")%>
	</p>

	<form action="LogoutSurvlet" method="get" align="center">

		<input type="submit" value="Logout">

	</form>



	<hr width="50%">

	<br>
	<h4 align="center">
		Boradcasts for
		<%=LocalDate.now()%>
	</h4>


	<table style="width: 50%" border="23%" align="center">
		<tr>
			<td><a href="http://localhost:8080/kinobotevgrad.bg/Movie1">
					<img border="23%" src="images/1.jpg" alt="avengers1" width="200"
					height="300" align="middle">
			</a></td>

			<td><a href="http://localhost:8080/kinobotevgrad.bg/Movie2">
					<img border="23%" src="images/2.jpg" alt="avengers2" width="200"
					height="300" align="middle">
			</a></td>

			<td><a href="http://localhost:8080/kinobotevgrad.bg/Movie3">
					<img border="23%" src="images/3.jpg" alt="avengers3" width="200"
					height="300" align="middle">
			</a></td>
		</tr>
		<tr>
			<td><a href="http://localhost:8080/kinobotevgrad.bg/Movie4">
					<img border="23%" src="images/4.jpg" alt="avengers1" width="200"
					height="300" align="middle">
			</a></td>


			<td><a href="http://localhost:8080/kinobotevgrad.bg/Movie5">
					<img border="23%" src="images/5.jpg" alt="avengers1" width="200"
					height="300" align="middle">
			</a></td>

			<td><a href="http://localhost:8080/kinobotevgrad.bg/Movie6">
					<img border="23%" src="images/6.jpg" alt="avengers1" width="200"
					height="300" align="middle">
			</a></td>
		</tr>
	</table>
	<br>
	<br>

	<p align="center">
		<a href="http://localhost:8080/kinobotevgrad.bg/"><u>Return to
				main page!</u></a>
	</p>

</body>
</html>