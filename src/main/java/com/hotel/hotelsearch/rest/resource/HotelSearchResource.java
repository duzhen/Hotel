package com.hotel.hotelsearch.rest.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.hotel.hotelsearch.ParamConflictException;
import com.hotel.hotelsearch.ParamException;
import com.hotel.hotelsearch.bean.APIError;
import com.hotel.hotelsearch.bean.Result;
import com.hotel.hotelsearch.entity.Hotel;
import com.hotel.hotelsearch.service.HotelSearchService;

@Component
@Path("rest")
public class HotelSearchResource {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HotelSearchService hotelSearchService;

	@GET
	@Path("search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchHotel(@QueryParam("city") String city, @QueryParam("country") String country) {
		List<Hotel> hotels = hotelSearchService.search(city, country);
		Result result = new Result(hotels);

		return Response.ok().entity(result).build();
	}

	@GET
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addHotelGet(@QueryParam("name") String name, @QueryParam("city") String city,
			@QueryParam("country") String country) {
		return add(name, city, country);
	}

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addHotel(@FormParam("name") String name, @FormParam("city") String city,
			@FormParam("country") String country) {
		return add(name, city, country);
	}

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addHotel(Hotel h) {
		return add(h.getName(), h.getCity(), h.getCountry());
	}

	private Response add(String name, String city, String country) {
		try {
			Hotel hotel = hotelSearchService.add(name, city, country);
			return Response.ok().entity(hotel).build();
		} catch (DataIntegrityViolationException e) {
			logger.info(e.getRootCause().getMessage());
		} catch (ParamConflictException e) {
			logger.info(e.getMessage());
			return Response.status(Status.CONFLICT)
					.entity(new APIError(Status.CONFLICT.getStatusCode(), Status.CONFLICT.getReasonPhrase())).build();
		} catch (ParamException e) {
			logger.info(e.getMessage());
			return Response.status(Status.BAD_REQUEST)
					.entity(new APIError(Status.BAD_REQUEST.getStatusCode(), Status.BAD_REQUEST.getReasonPhrase()))
					.build();
		}
		return Response.status(Status.BAD_REQUEST)
				.entity(new APIError(Status.BAD_REQUEST.getStatusCode(), Status.BAD_REQUEST.getReasonPhrase())).build();
	}
}