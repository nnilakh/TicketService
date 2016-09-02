package org.walmart.ticketrvice.ticket_service;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.walmart_ticketService.model.Venue;
import org.walmart_ticketService.repository.RepositoryAvailableSeats;

public class RepositoryAvailableSeatsTest {

	public RepositoryAvailableSeats repositoryAvailableSeats;
	public Venue venue;
	
	@Before
	public void init(){
		venue = new Venue();
		repositoryAvailableSeats = new RepositoryAvailableSeats(venue);
	}
	
	@Test
	public void findAllTest(){
		assertEquals(repositoryAvailableSeats.findAll().size(),6250);
	}
	
	@Test
	public void findAllLevelTest(){
		assertEquals(repositoryAvailableSeats.findAllLevel(2).size(),2000);
	}
	
	@Test
	public void findAllInRangeTest(){
		assertEquals(repositoryAvailableSeats.findAllInRange(1, 4).size(),6250);
	}
	
}
