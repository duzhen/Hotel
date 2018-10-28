package com.hotel.hotelsearch.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotelsearch.entity.Hotel;
import com.hotel.hotelsearch.reporsitory.HotelRepository;

@Service
public class HotelSearchService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private HotelRepository hotelRepository;
	
	public Hotel add(String name, String city, String country) {
		Hotel h = new Hotel();
		h.setName(name);
		h.setCity(city);
		h.setCountry(country);
		return hotelRepository.save(h);
	}
	public List<Hotel> search(String city, String country) {
		return hotelRepository.findHotel(city, country);
	}
}