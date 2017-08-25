package com.fh;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
@SessionAttributes("myRequestObject")
public class LoginController {
	private Client client;
	private String REST_SERVICE_URL = "http://localhost:9080/CardRestService/rest/CardService";
	private static final String SUCCESS_RESULT = "<response>SUCCESS</response>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	@ModelAttribute("myRequestObject")
	public Reservation modelSetup() {
		Reservation bean = new Reservation();
		return bean;
	}

	@RequestMapping(value = "/payment")
	public String processPayment(HttpServletRequest request, HttpServletResponse response) {
		String card_no = request.getParameter("card_no");
		String ccv = request.getParameter("ccv");
		String pnr = null;

		Form form = new Form();
		form.param("card_no", card_no);
		form.param("ccv", ccv);

		String callResult = client.target(REST_SERVICE_URL + "/checkCard").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		// TODO: Save information to the Database
		String result = FAIL;
		if (SUCCESS_RESULT.equals(callResult)) {
			pnr = randomPNRGenerator(6);
			request.setAttribute("PNR", pnr);
			return "confirmation";
		}
		else {
			return "payment";
		}

		// }
	}

	@ModelAttribute("myRequestObject-y")
	@RequestMapping(value = "/passenger") // TODO: Passenger
	public ModelAndView processPassenger(@ModelAttribute("myRequestObject") Reservation requestObj, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		String username = request.getParameter("username");
		int phone_no = Integer.parseInt(request.getParameter("phone_no"));
		String email = request.getParameter("email");
		int age = Integer.parseInt(request.getParameter("age"));

		requestObj.setUsername(username);
		System.out.println(requestObj.getUsername());

		// Reservation reserve = (Reservation) session.getAttribute(s);
		// reserve.setUsername(username);
		// Passenger passenger = new Passenger(username, phone_no, email, age);
		// request.setAttribute("Passenger", passenger);

		// return "payment";

		ModelAndView mv = new ModelAndView();
		mv.setViewName("payment");
		// mv.addObject("id", id);
		return mv;
	}

	// @RequestMapping(value = "/passenger") // TODO: Passenger
	// public String processPassenger(HttpServletRequest request,
	// HttpServletResponse response, HttpSession session) {
	//
	// String username = request.getParameter("username");
	// int phone_no = Integer.parseInt(request.getParameter("phone_no"));
	// String email = request.getParameter("email");
	// int age = Integer.parseInt(request.getParameter("age"));
	//
	// // requestObj.setUsername(username);
	// // System.out.println(requestObj.getUsername());
	//
	// // Reservation reserve = (Reservation) session.getAttribute(s);
	// // reserve.setUsername(username);
	// Passenger passenger = new Passenger(username, phone_no, email, age);
	// request.setAttribute("Passenger", passenger);
	//
	// return "payment";
	// }

	// @ModelAttribute("myRequestObject-x")
	@RequestMapping(value = "/selectFlight") // TODO: Select Flight
	public ModelAndView processSelect(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Inside ProcessFlight");
		int id = Integer.parseInt(request.getParameter("fselect"));
		// get the number of tickets to check seat availability. If available then
		// subtract during reservation. Otherwise return to flight.jsp page
		System.out.println("ID chosen: " + id);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("passenger");
		mv.addObject("id", id);
		return mv;
		// return "passenger";
	}

	@RequestMapping(value = "/searchFlight") // TODO: SearchFlight
	public ModelAndView processSearch(HttpServletRequest request, HttpServletResponse response) {
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");

		ModelAndView mv = new ModelAndView();
		ManageFlight flight = new ManageFlight();

		System.out.println("Inside LoginController");
		List flightList = flight.listFlight(source, destination);

		if (!flightList.isEmpty()) {
			mv.setViewName("flights");
			mv.addObject("flightList", flightList);
		} else {
			mv.setViewName("index");
		}

		return mv;

	}
	
	public String randomPNRGenerator(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

}
