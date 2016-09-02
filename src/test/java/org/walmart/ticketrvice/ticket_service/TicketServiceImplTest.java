package org.walmart.ticketrvice.ticket_service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.walmart_ticketService.model.SeatHold;
import org.walmart_ticketService.model.Venue;
import org.walmart_ticketService.repository.RepositoryAvailableSeats;
import org.walmart_ticketService.repository.RepositoryHeldSeats;
import org.walmart_ticketService.services.TicketServiceImp;

public class TicketServiceImplTest {
	private TicketServiceImp ticketServiceImp;
	private Venue venue;
	private RepositoryAvailableSeats repositoryAvailableSeats;
	private RepositoryHeldSeats repositoryHeldSeats;

	@Before
	public void init(){
		venue = new Venue();
		repositoryAvailableSeats = new RepositoryAvailableSeats(venue);
		repositoryHeldSeats = new RepositoryHeldSeats();
		ticketServiceImp = new TicketServiceImp(repositoryAvailableSeats, repositoryHeldSeats, venue);
	}
	
	@Test
	public void numberSeatsAvailable(){
		
		assertEquals(ticketServiceImp.numSeatsAvailable(Optional.of(2)),2000);
		assertEquals(ticketServiceImp.numSeatsAvailable(Optional.empty()),6250);
		
	}
	
	@Test
   public void findAndHoldSeats(){
		SeatHold seatHold = ticketServiceImp.findAndHoldSeats(5, Optional.of(1), Optional.of(4), "niki.niki@gmail.com");
		assertEquals(seatHold.getCustomerEmail(), "niki.niki@gmail.com");
   }

	@Test
	public void reserveSeatsTest(){
		SeatHold seatHold = ticketServiceImp.findAndHoldSeats(5, Optional.of(1), Optional.of(4), "niki.niki@gmail.com");
		assert(ticketServiceImp.reserveSeats(seatHold.getSeatHoldId(), "niki.niki@gmail.com")!=null);
	}

}
