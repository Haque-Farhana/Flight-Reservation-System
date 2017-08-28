package com.fh;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
@SessionAttributes("myRequestObject")
public class FlightController {
	private Client client;
	private String REST_SERVICE_URL = "http://localhost:9080/CardRestService/rest/CardService";
	private static final String SUCCESS_RESULT = "<response>SUCCESS</response>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";
	
	// ExService cardService =new ExtService();
	private void init() {
		this.client = ClientBuilder.newClient();
	}

	@ModelAttribute("myRequestObject")
	public Reservation modelSetup() {
		Reservation bean = new Reservation();
		return bean;
	}

	@RequestMapping(value = "/payment")
	public ModelAndView processPayment(@ModelAttribute("myRequestObject") Reservation requestObj, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String card_no = request.getParameter("card_no");
		String ccv = request.getParameter("ccv");
		String amount = requestObj.getPrice() + "";

		ModelAndView mv = new ModelAndView();

		if (!Pattern.matches("[0-9]{9}", card_no) || !Pattern.matches("[0-9]{3}", ccv)) {
			System.out.println("Invalid card or CVV number!!");
			mv.setViewName("payment");
			return mv;
		}

		ExternalCardService cardService = new ExternalCardService();
		if (cardService.isCardValid(card_no, ccv, amount)) {
			requestObj.setPnr(randomPNRGenerator(6));
			mv.setViewName("confirmation");
			mv.addObject("PNR", requestObj.getPnr());

			ManageReservation reserve = new ManageReservation();
			System.out.println(requestObj.getUsername());
			System.out.println(requestObj.getPnr());
			System.out.println(requestObj.getFlight_no());
			System.out.println(requestObj.getNo_of_tickets());
			System.out.println(requestObj.getPrice());
			int reserveID = reserve.addReservation(requestObj);
			System.out.println(reserveID);
		} else {
			mv.setViewName("payment");
		}
		return mv;
	}

	@RequestMapping(value = "/passenger")
	public ModelAndView processPassenger(@ModelAttribute("myRequestObject") Reservation requestObj, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		String username = request.getParameter("username");
		int phone_no = Integer.parseInt(request.getParameter("phone_no"));
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));

		requestObj.setUsername(username);
		System.out.println("Username: " + requestObj.getUsername());
		System.out.println("Flight_no: " + requestObj.getFlight_no());
		System.out.println("No. of tickets: " + requestObj.getNo_of_tickets());
		System.out.println("Total price: " + requestObj.getPrice());

		ModelAndView mv = new ModelAndView();
		mv.setViewName("payment");

		return mv;
	}

	@RequestMapping(value = "/selectFlight")
	public ModelAndView processSelect(@ModelAttribute("myRequestObject") Reservation requestObj, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		int id = Integer.parseInt(request.getParameter("fselect"));

		ManageFlight mf = new ManageFlight();
		FlightDetail flight = mf.getFlight(id);
		double total_price = requestObj.getNo_of_tickets() * flight.getPrice();
		requestObj.setFlight_no(flight.getFlight_no());
		requestObj.setPrice(total_price);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("passenger");

		return mv;
	}

	@RequestMapping(value = "/searchFlight")
	public ModelAndView processSearch(@ModelAttribute("myRequestObject") Reservation requestObj, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		int no_of_tickets = Integer.parseInt(request.getParameter("ticket"));

		ModelAndView mv = new ModelAndView();
		ManageFlight flight = new ManageFlight();

		List flightList = flight.listFlight(source, destination, no_of_tickets);

		requestObj.setNo_of_tickets(no_of_tickets);
		System.out.println(requestObj.getNo_of_tickets());

		if (!flightList.isEmpty()) {
			mv.setViewName("flights");
			mv.addObject("flightList", flightList);
		} else {
			mv.setViewName("index");
		}

		return mv;

	}
	
	public String randomPNRGenerator(int count) {
		final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

}
