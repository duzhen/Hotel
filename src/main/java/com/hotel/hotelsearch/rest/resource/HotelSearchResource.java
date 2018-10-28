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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
		List<Hotel> hotels = hotelSearchService.search("%"+city+"%", "%"+country+"%");
		Result result = new Result(hotels);
				
		return Response.ok().entity(result).build();
    }

    @GET
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHotelGet(@QueryParam("name") String name, @QueryParam("city") String city, @QueryParam("country") String country) {
    	Hotel hotel = hotelSearchService.add(name, city, country);
    	return Response.ok().entity(hotel).build();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHotel(@FormParam("name") String name, @FormParam("city") String city,
    		@FormParam("country") String country) {
    	Hotel hotel = hotelSearchService.add(name, city, country);
    	return Response.ok().entity(hotel).build();
    }
    
    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHotel(Hotel h) {
    	Hotel hotel = hotelSearchService.add(h.getName(), h.getCity(), h.getCountry());
    	return Response.ok().entity(hotel).build();
    }
}