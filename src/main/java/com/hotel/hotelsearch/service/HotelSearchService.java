package com.hotel.hotelsearch.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotelsearch.ParamConflictException;
import com.hotel.hotelsearch.ParamException;
import com.hotel.hotelsearch.entity.Hotel;
import com.hotel.hotelsearch.mapper.HotelSearchMapper;

@Service
public class HotelSearchService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HotelSearchMapper hotelMapper;

	public Hotel add(String name, String city, String country) throws ParamException {
		if(!Pattern.matches("[a-zA-Z ]{1,45}", Optional.ofNullable(name).orElse("")) || !Pattern.matches("[a-zA-Z ]{1,45}", Optional.ofNullable(city).orElse("")) 
				|| !Pattern.matches("[a-zA-Z ]{1,45}", Optional.ofNullable(country).orElse(""))) {
			throw new ParamException("illegal parameter");
		}
		if(!Pattern.matches("argentina|brazil|canada", Optional.ofNullable(country).orElse("").toLowerCase())) {
			throw new ParamConflictException("conflict country");
		}
		Hotel h = new Hotel();
		h.setName(WordUtils.capitalize(name.toLowerCase()));
		h.setCity(WordUtils.capitalize(city.toLowerCase()));
		h.setCountry(WordUtils.capitalize(country.toLowerCase()));
		hotelMapper.addHotel(h);
		return h;
	}

	public List<Hotel> search(String city, String country) {
		logger.info("city: "+city+" country: "+country);
		if(Optional.ofNullable(city).isPresent()) {
			city = "%"+city+"%";
		} else {
			city = "%";
		}
		if(Optional.ofNullable(country).isPresent()) {
			country = "%"+country+"%";
		} else {
			country = "%";
		}
		logger.info("after update city: "+city+" country: "+country);
		
		List<Hotel> hotels = hotelMapper.findHotel(city, country);

		return hotels.stream().sorted((Hotel arg0, Hotel arg1) -> {
			int name = arg0.getName().compareTo(arg1.getName());
			if (name == 0) {
				return arg0.getCity().compareTo(arg1.getCity());
			} else {
				return name;
			}
		}).collect(Collectors.toList());

	}
}