package com.hotel.hotelsearch.unit;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hotel.hotelsearch.entity.Hotel;
import com.hotel.hotelsearch.mapper.HotelSearchMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelSearchMapperUnitTest {
	@Autowired
	private HotelSearchMapper hotelMapper;
	
	@Test
	public void testAddHotel() {
		Hotel hotel = new Hotel();
		hotel.setName("1Hotel");
		hotel.setCity("Buenos Aires");
		hotel.setCountry("Argentina");
		int id = hotelMapper.addHotel(hotel);
		assertTrue("should add hotel success", id > 0);
	}
	
	@Test
	public void testSearchHotel() {
		List<Hotel> hotels = hotelMapper.findHotel("Buenos Aires", "Argentina");
		assertTrue("at least one hotel in Buenos Aires, Argentina", hotels.size() > 0);
	}
}
