<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
</head>
<body>
<h1>Confirm Payment</h1>
	<form action="payment.do" method="post">
		Card number:
	  	<input type="text" name="card_no" value="">
	  	<br><br>
	  	CVV:
	  	<input type="password" name="ccv" value="">
	  	<br><br>
	  	<input type="submit" value="Submit">
	</form>

</body>
</html>