package com.hotel.hotelsearch;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.hotel.hotelsearch.rest.resource.HotelSearchResource;

@Component
public class JerseyConfig extends ResourceConfig {

   public JerseyConfig() {
	   register(HotelSearchResource.class);
   }

}