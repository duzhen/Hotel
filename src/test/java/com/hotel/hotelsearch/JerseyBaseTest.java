package com.hotel.hotelsearch;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelSearchApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract public class JerseyBaseTest {

	@Value("${local.server.port}")
	private int port;

	public Response get(String path) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + port + "/rest" + path);
		return target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get();
	}

	public Response post(String path, Form form) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + port + "/rest" + path);
		return target.request(MediaType.APPLICATION_FORM_URLENCODED).accept(MediaType.APPLICATION_JSON)
				.post(Entity.form(form));
	}
	
	public Response post(String path, String json) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:" + port + "/rest" + path);
		return target.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(Entity.json(json));
	}
}
