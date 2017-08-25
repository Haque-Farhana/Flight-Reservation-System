<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Info</title>

</head>
<body>
<form action="passenger.do" method="POST">
	Username:
 	<input type="text" name="username" value="">
 	<br><br>
 	Phone number:
 	<input type="text" name="phone_no" value="">
 	<br><br>
 	Email:
 	<input type="text" name="email" value="">
 	<br><br>
 	Age:
 	<input type="text" name="age" value="">
 	<br><br>
 	<input type="submit" value="Submit">			
</form>	
</body>
</html>