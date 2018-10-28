package com.hotel.hotelsearch.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hotel.hotelsearch.entity.Hotel;
import com.hotel.hotelsearch.repository.HotelRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelSearchRepositoryUnitTest {
	@Autowired
	private HotelRepository hotelRepository;
	
	@Test
	public void testAddHotel() {
		Hotel hotel = new Hotel();
		hotel.setName("1Hotel");
		hotel.setCity("Buenos Aires");
		hotel.setCountry("Argentina");
		Hotel h = hotelRepository.save(hotel);
		assertEquals("should be the same hotel name", h.getName(), hotel.getName());
	}
	
	@Test
	public void testSearchHotel() {
		List<Hotel> hotels = hotelRepository.findHotel("Buenos Aires", "Argentina");
		assertTrue("at least one hotel in Buenos Aires, Argentina", hotels.size() > 0);
	}
}
