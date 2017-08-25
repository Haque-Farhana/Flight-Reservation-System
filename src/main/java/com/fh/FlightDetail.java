package com.fh;

public class FlightDetail {
	private String source;
	private String destination;
	private String flight_no;
	private int id;
	private String date;
	private String departure_time;
	private String arrival_time;
	private int no_of_seats;
	private double price;

	public FlightDetail() {

	}

	public FlightDetail(String source, String destination, String flight_no) {
		this.source = source;
		this.destination = destination;
		this.flight_no = flight_no;
	}

	public FlightDetail(String source, String destination, String flight_no, String date, String departure_time,
			String arrival_time, int no_of_seats, double price, int id) {
		this.source = source;
		this.destination = destination;
		this.flight_no = flight_no;
		this.date = date;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
		this.no_of_seats = no_of_seats;
		this.price = price;
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFlight_no() {
		return flight_no;
	}

	public void setFlight_no(String flight_no) {
		this.flight_no = flight_no;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}

	public String getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	public int getNo_of_seats() {
		return no_of_seats;
	}

	public void setNo_of_seats(int no_of_seats) {
		this.no_of_seats = no_of_seats;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
