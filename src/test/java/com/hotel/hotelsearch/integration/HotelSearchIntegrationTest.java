package com.hotel.hotelsearch.integration;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import com.hotel.hotelsearch.JerseyBaseTest;

public class HotelSearchIntegrationTest extends JerseyBaseTest {

	@Test
	public void testAddHotelGetMethod() {
		Response response = get("/add?name=Hotel&city=Montreal&country=Canada");
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testAddHotelPostMethod() {
		Form form = new Form();
	    form.param("name", "Hotel");
	    form.param("city", "Montreal");
	    form.param("country", "Canada");
		Response response = post("/add", form);
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testAddHotelPostJsonMethod() {
		Response response = post("/add", "{\"name\": \"Hotel\", \"city\": \"Montreal\", \"country\": \"Canada\"}");
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testSearchHotelGetMethod() {
		Response response = get("/search?city=Montreal&country=Canada");
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}
}
