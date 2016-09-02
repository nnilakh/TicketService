package org.walmart_ticketService.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SeatHold {
	
	private static final AtomicInteger uniqueId = new AtomicInteger();
	private int seatHoldId;
	private String customerEmail;
	private LocalDateTime timeOfTicketHold;
	private String reservationConfirmationCode;
	private Set<Seat> seatsOnHold;
	
	public SeatHold( String customerEmail,  Set<Seat> seatsOnHold,String reservationConfirmationCode) {
		this.seatHoldId = SeatHold.nextUniqueId();
		this.customerEmail = customerEmail;
		this.timeOfTicketHold = LocalDateTime.now();
		this.seatsOnHold = seatsOnHold;
		this.reservationConfirmationCode = reservationConfirmationCode;
	}
	
	public SeatHold(String customerEmail,  Set<Seat> seatsOnHold,String reservationConfirmationCode,int seatHoldId){
		this.seatHoldId = seatHoldId;
		this.customerEmail = customerEmail;
		this.timeOfTicketHold = LocalDateTime.now();
		this.seatsOnHold = seatsOnHold;
		this.reservationConfirmationCode= reservationConfirmationCode;
	}
	
	public SeatHold( String customerEmail, Set<Seat> seatsOnHold) {
		this.seatHoldId =  SeatHold.nextUniqueId();;
		this.customerEmail = customerEmail;
		this.seatsOnHold = seatsOnHold;
		this.timeOfTicketHold = LocalDateTime.now();
	}
	
	public int getSeatHoldId() {
		return seatHoldId;
	}
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	
	public LocalDateTime getTimeOfTicketHold() {
		return timeOfTicketHold;
	}
	
	public String getReservationConfirmationCode() {
		return reservationConfirmationCode;
	}
	
	public Set<Seat> getSeatsOnHold() {
		return seatsOnHold;
	}
	
	public static int nextUniqueId(){
    	return uniqueId.getAndIncrement();
    }
}
