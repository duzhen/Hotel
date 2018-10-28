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
import com.hotel.hotelsearch.service.HotelSearchService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelSearchServiceUnitTest {
	@Autowired
	private HotelSearchService hotelSearchService;
	
	@Test
	public void testAddHotel() {
		Hotel h = hotelSearchService.add("AHotel", "Buenos Aires", "Argentina");
		assertEquals("should be the same hotel name", h.getName(), "AHotel");
	}
	
	@Test
	public void testSearchHotelInOrder() {
		hotelSearchService.add("AHotel", "Buenos Aires", "Argentina");
		hotelSearchService.add("1Hotel", "Buenos Aires", "Argentina");
		List<Hotel> hotels = hotelSearchService.search("Buenos Aires", "Argentina");
		hotels.forEach(System.out::println);
		boolean order = true;
		for(int i=0;i<hotels.size()-1;i++) {
			int name = hotels.get(i).getName().compareTo(hotels.get(i+1).getName());
			if(name == 0) {
				if(hotels.get(i).getName().compareTo(hotels.get(i+1).getName()) > 0) {
					order = false;
					break;
				}
			} else if(name > 0){
				order = false;
				break;
			}
		}
		assertTrue("at least two hotel in Buenos Aires, Argentina", hotels.size() > 2);
		assertEquals("hotel should return in order", order, true);
	}
}
