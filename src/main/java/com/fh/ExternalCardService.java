package com.fh;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class ExternalCardService {
	private Client client;
	private String REST_SERVICE_URL = "http://localhost:9080/CardRestService/rest/CardService";
	private static final String SUCCESS_RESULT = "<response>SUCCESS</response>";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";

	public ExternalCardService() {
		this.client = ClientBuilder.newClient();
	}

	public boolean isCardValid(String card_no, String ccv, String amount) {
		Form form = new Form();
		form.param("card_no", card_no);
		form.param("ccv", ccv);
		form.param("amount", amount);

		this.client = ClientBuilder.newClient();
		String callResult = client.target(REST_SERVICE_URL + "/validateCard").request(MediaType.APPLICATION_XML)
				.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		// TODO: Save information to the Database
		String result = FAIL;
		if (SUCCESS_RESULT.equals(callResult)) {
			return true;
		} else {
			return false;
		}
	}
	

}
