package com.work.classes;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.work.classes.dao.ReservationRepository;
import com.work.classes.service.ReservationTerrainRestService;

public class TestReservation {

	ReservationTerrainRestService reservationService = null;
	ReservationRepository repositoryRes = mock(ReservationRepository.class); 
	
	@Before
	public void setUp() {
		reservationService = new ReservationTerrainRestService(repositoryRes);
	}
	
	@Test
	public void testGet() {
		/*try {
			//when(repositoryRes.findByOptions(1, "2018-4-25").isEmpty()).thenReturn(null);
			//Assert.assertNull(reservationService.find("Football", "2018-4-25"));
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		fail("Not yet implemented");
		//when(reservationService.save()).thenReturns("ok");
	}

}
