package com.hotel.hotelsearch.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotelsearch.entity.Hotel;
import com.hotel.hotelsearch.repository.HotelRepository;

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
		List<Hotel> hotels = hotelRepository.findHotel(city, country);
//		return hotels.stream().sorted(new Comparator<Hotel>() {
//
//			@Override
//			public int compare(Hotel arg0, Hotel arg1) {
//				int name = arg0.getName().compareTo(arg1.getName());
//				if(name == 0) {
//					return arg0.getCity().compareTo(arg1.getCity());
//				} else {
//					return name;
//				}
//			}
//			
//		}).collect(Collectors.toList());
		
//		hotels.sort(new Comparator<Hotel>() {
//
//			@Override
//			public int compare(Hotel arg0, Hotel arg1) {
//				int name = arg0.getName().compareTo(arg1.getName());
//				if(name == 0) {
//					return arg0.getCity().compareTo(arg1.getCity());
//				} else {
//					return name;
//				}
//			}
//			
//		});
		
		return hotels;
	}
}