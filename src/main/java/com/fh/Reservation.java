package com.fh;

public class Reservation {
	private int id;
	private String pnr;
	private String username;
	private String flight_no;
	private int no_of_tickets;
	private double price;

	public Reservation() {

	}

	public Reservation(String pnr, String username, String flight_no, int no_of_tickets, double price) {
		this.pnr = pnr;
		this.username = username;
		this.flight_no = flight_no;
		this.no_of_tickets = no_of_tickets;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFlight_no() {
		return flight_no;
	}

	public void setFlight_no(String flight_no) {
		this.flight_no = flight_no;
	}

	public int getNo_of_tickets() {
		return no_of_tickets;
	}

	public void setNo_of_tickets(int no_of_tickets) {
		this.no_of_tickets = no_of_tickets;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
