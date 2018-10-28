package com.hotel.hotelsearch.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hotel.hotelsearch.ParamConflictException;
import com.hotel.hotelsearch.ParamException;
import com.hotel.hotelsearch.entity.Hotel;
import com.hotel.hotelsearch.service.HotelSearchService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotelSearchServiceUnitTest {
	@Autowired
	private HotelSearchService hotelSearchService;
	
	@Test
	public void testAddHotelInNormal() {
		Hotel h = hotelSearchService.add("Hotel Argentina", "Buenos Aires", "Argentina");
		assertEquals("country Argentina is valide", h.getCountry(), "Argentina");
		
		h = hotelSearchService.add("Hotel Brazil", "Rio de Janeiro", "Brazil");
		assertEquals("country Brazil is valide", h.getCountry(), "Brazil");
		
		h = hotelSearchService.add("Hotel Canada", "Montreal", "Canada");
		assertEquals("country Canada is valide", h.getCountry(), "Canada");
		
	}
	
	@Test
	public void testAddHotelWithParameterError() {
		Hotel h = null;
		try {
			h = hotelSearchService.add("Hotel1", "Buenos Aires", "Argentina");
			assertFalse("hotel name contain non-english characters but cannot find it", true);
		} catch(ParamException e) {
			assertEquals("hotel name contain non-english characters", e.getMessage(), "illegal parameter");
		}
		try {
			h = hotelSearchService.add("Hotel", "Buenos Aires--", "Argentina");
			assertFalse("city name contain non-english characters but cannot find it", true);
		} catch(ParamException e) {
			assertEquals("city name contain non-english characters", e.getMessage(), "illegal parameter");
		}
		try {
			h = hotelSearchService.add("Hotel", "Buenos Aires", "Argentina&%$$%");
			assertFalse("country name contain non-english characters but cannot find it", true);
		} catch(ParamException e) {
			assertEquals("country name contain non-english characters", e.getMessage(), "illegal parameter");
		}
		
	}
	
	@Test
	public void testAddHotelWithConflictError() {
		try {
			Hotel h = hotelSearchService.add("Hotel", "Buenos Aires", "another Argentina");
			assertFalse("invalid country name, but cannot find it", true);
		} catch(ParamConflictException e) {
			assertEquals("invalid country name", e.getMessage(), "conflict country");
		}
	}
	
	@Test
	public void testSearchHotelInOrder() {
		hotelSearchService.add("bHotel", "Buenos Aires", "Argentina");
		hotelSearchService.add("AHotel", "Buenos AiresZ", "Argentina");
		hotelSearchService.add("AHotel", "Buenos AiresA", "Argentina");
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
		assertTrue("should have at least two hotel in Buenos Aires, Argentina", hotels.size() > 2);
		assertEquals("hotel should return in order", order, true);
	}
	
	@Test
	public void testSearchHotelSQLSafe() {
		hotelSearchService.add("AHotel", "Buenos Aires", "Argentina");
		hotelSearchService.add("bHotel", "Buenos Aires", "Argentina");
		List<Hotel> hotels = hotelSearchService.search("Buenos Aires", "Argentina LIMIT 1");

		assertTrue("should have zero hotel in Argentina LIMIT 1", hotels.size() == 0);
	}
}
