package org.walmart.ticketrvice.ticket_service;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.walmart_ticketService.model.Seat;
import org.walmart_ticketService.model.SeatHold;
import org.walmart_ticketService.repository.RepositoryHeldSeats;

public class RepositoryHeldSeatsTest {
	
	public RepositoryHeldSeats repositoryHeldSeats;
	
	@Before
	public void init(){
		repositoryHeldSeats = new RepositoryHeldSeats();
	}
	
	@Test
	public void findTest(){
		Set<Seat> seatHold = new HashSet<Seat>();
		seatHold.add(new Seat(12));
		SeatHold seatHold1 = new  SeatHold("niki.niki@gmail.com", seatHold);
		SeatHold seatHold2 = new  SeatHold("niki.niki1@gmail.com", seatHold);
		SeatHold seatHold3 = new  SeatHold("niki.niki2@gmail.com", seatHold);
		repositoryHeldSeats.saveSeatHold(seatHold1);
		repositoryHeldSeats.saveSeatHold(seatHold2);
		repositoryHeldSeats.saveSeatHold(seatHold3);
		
		assertEquals(repositoryHeldSeats.find(0).getCustomerEmail(),"niki.niki@gmail.com");
		assertEquals(repositoryHeldSeats.find(1).getCustomerEmail(),"niki.niki1@gmail.com");
		assertEquals(repositoryHeldSeats.find(2).getCustomerEmail(),"niki.niki2@gmail.com");
	}
	
	@Test 
	public void findExpired() throws InterruptedException{
		Set<Seat> seatHold = new HashSet<Seat>();
		seatHold.add(new Seat(12));
		SeatHold seatHold1 = new  SeatHold("niki.niki@gmail.com", seatHold);
		SeatHold seatHold2 = new  SeatHold("niki.niki1@gmail.com", seatHold);
		SeatHold seatHold3 = new  SeatHold("niki.niki2@gmail.com", seatHold);
		repositoryHeldSeats.saveSeatHold(seatHold1);
		repositoryHeldSeats.saveSeatHold(seatHold2);
		repositoryHeldSeats.saveSeatHold(seatHold3);
		Thread.sleep(1000);
		assertEquals(repositoryHeldSeats.findExpired(0).size(),3);
	}
//	
	@Test 
	public void saveSeatHoldTest(){
		
		Set<Seat> seatHold = new HashSet<Seat>();
		seatHold.add(new Seat(12));
		SeatHold seatHold1 = new  SeatHold("niki.niki@gmail.com", seatHold,"123",1000);
		repositoryHeldSeats.saveSeatHold(seatHold1);
		
		assertEquals(repositoryHeldSeats.find(1000).getCustomerEmail(),"niki.niki@gmail.com");
		
	}
	
	
	@Test 
	public void removeSeatHoldTest(){
		Set<Seat> seatHold = new HashSet<Seat>();
		seatHold.add(new Seat(12));
		SeatHold seatHold1 = new  SeatHold("niki.niki@gmail.com", seatHold,"123",1001);
		repositoryHeldSeats.saveSeatHold(seatHold1);
		repositoryHeldSeats.removeSeatHold(1001);
		
		
		assertEquals(repositoryHeldSeats.find(1000),null);
	}

}
