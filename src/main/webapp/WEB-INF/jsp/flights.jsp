<%@page import="com.fh.FlightDetail"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Search Result</title>
</head>
<body>
	<form action="selectFlight.do" method="POST">
	<style>
		table, th, td {
		    border-collapse: collapse;
		}
		th, td {
		    padding: 5px;
		}
		th {
		    text-align: left;
		}
	</style>
		<table style="width:80%">
		<tr>
			<th></th>
			<th>Origin</th>
			<th>Destination</th>
			<th>Flight Number</th>
			<th>Date</th>
			<th>Departure Time</th>
			<th>Arrival Time</th>
			<th>Seats Available</th>
			<th>Ticket Price</th>
		</tr>
			<% ArrayList<FlightDetail> fList = (ArrayList<FlightDetail>) request.getAttribute("flightList");
    		for(FlightDetail flight:fList){
    		%>
	        	<tr>
	        		<td><input type="radio" value=<%=flight.getId()%> name="fselect"></td>
		            <td><%=flight.getSource()%></td>
		            <td><%=flight.getDestination() %></td> 
		            <td><%=flight.getFlight_no() %></td>
		            <td><%=flight.getDate() %></td>
		            <td><%=flight.getDeparture_time() %></td>
		            <td><%=flight.getArrival_time() %></td>
		            <td><%=flight.getNo_of_seats() %></td>
		            <td><%=flight.getPrice() %></td>
	        	</tr>
      		<%}%>
		</table><br>
		<input type="submit" value="Book flight">	
	</form>
		  
</body>
</html>