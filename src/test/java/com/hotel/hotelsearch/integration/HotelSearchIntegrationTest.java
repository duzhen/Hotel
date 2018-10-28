package com.hotel.hotelsearch.integration;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import com.hotel.hotelsearch.JerseyBaseTest;

public class HotelSearchIntegrationTest extends JerseyBaseTest {

	@Test
	public void testAddHotelGetMethod() {
		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("name", "Hotel Argentina");
		requestParams.put("city", "Buenos Aires");
		requestParams.put("country", "Argentina");
	    
		Response response = get(requestParams, "add");
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		
		requestParams.put("name", "Hotel Brazil");
		requestParams.put("city", "Rio de Janeiro");
		requestParams.put("country", "Brazil");
		response = get(requestParams, "add");
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		
		requestParams.put("name", "Hotel Canada");
		requestParams.put("city", "Montreal");
		requestParams.put("country", "Canada");
		response = get(requestParams, "add");
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testAddHotelGetMethodWithParameterError() {
		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("name", "Hotel1");
		requestParams.put("city", "Buenos Aires");
		requestParams.put("country", "Argentina");
		Response response = get(requestParams, "add");
		assertEquals("Http Response should be 400: ", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	
		requestParams.put("name", "Hotel Argentina");
		requestParams.put("city", "Buenos---Aires");
		requestParams.put("country", "Argentina");
		response = get(requestParams, "add");
		assertEquals("Http Response should be 400: ", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		
		requestParams.put("name", "Hotel Argentina");
		requestParams.put("city", "Buenos Aires");
		requestParams.put("country", "#%^()*&\\@$!\\Argentina");
		response = get(requestParams, "add");
		assertEquals("Http Response should be 400: ", Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testAddHotelGetMethodwithConflictError() throws URISyntaxException {
		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("name", "Hotel");
		requestParams.put("city", "Montreal");
		requestParams.put("country", "Up Canada");
		Response response = get(requestParams, "add");
		assertEquals("Http Response should be 409: ", Status.CONFLICT.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testAddHotelPostMethod() {
		Form form = new Form();
		form.param("name", "Hotel");
		form.param("city", "Buenos Aires");
		form.param("country", "Argentina");
		Response response = post("add", form);
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testAddHotelPostJsonMethod() {
		Response response = post("add", "{\"name\": \"Hotel\", \"city\": \"Buenos Aires\", \"country\": \"Argentina\"}");
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testSearchHotelGetMethod() throws URISyntaxException {
		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("city", "Buenos Aires");
		requestParams.put("country", "Argentina");
		Response response = get(requestParams, "search");
		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
	}
}
