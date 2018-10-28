package com.hotel.hotelsearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hotel.hotelsearch.entity.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

	@Query(value = "SELECT name,city,country FROM #{#entityName} WHERE city LIKE :city and country LIKE :country ORDER BY name, city")
	List<Hotel> findHotel(@Param("city") String city, @Param("country") String country);
}