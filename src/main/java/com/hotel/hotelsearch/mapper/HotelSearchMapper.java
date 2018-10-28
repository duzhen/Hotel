package com.hotel.hotelsearch.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import com.hotel.hotelsearch.entity.Hotel;

@Mapper
public interface HotelSearchMapper {
	@Select(value = "SELECT * FROM hotel WHERE city LIKE #{city} and country LIKE #{country}")
	List<Hotel> findHotel(@Param("city") String city, @Param("country") String country);
	
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("INSERT into hotel (name,city,country)values(#{name},#{city},#{country})")
	int addHotel(Hotel hotel);
}