package org.walmart_ticketService.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.walmart_ticketService.model.Seat;
import org.walmart_ticketService.model.SeatHold;
import org.walmart_ticketService.model.SeatStatus;
import org.walmart_ticketService.model.Venue;
import org.walmart_ticketService.repository.RepositoryAvailableSeats;
import org.walmart_ticketService.repository.RepositoryHeldSeats;
import org.walmart_ticketService.utils.GenerateConfirmationCode;

public class TicketServiceImp implements TicketService {
	private RepositoryAvailableSeats repositoryAvailableSeats;
	private RepositoryHeldSeats repositoryHeldSeats;
	private Venue venue;

	public TicketServiceImp(RepositoryAvailableSeats repositoryAvailableSeats,
			RepositoryHeldSeats repositoryHeldSeats, Venue venue) {
		this.repositoryAvailableSeats = repositoryAvailableSeats;
		this.repositoryHeldSeats = repositoryHeldSeats;
		this.venue = venue;
	}
	//Return total number of available seats 
	public int numSeatsAvailable(Optional<Integer> venueLevel) {
		removeExpiredSeats();
		if (venueLevel.isPresent()) {
			return repositoryAvailableSeats.findAllLevel(venueLevel.get())
					.size();
		}
		return repositoryAvailableSeats.findAll().size();
	}
	
	//Remove the held seats that have exceeded the time limit
	private void removeExpiredSeats() {
		Set<SeatHold> expiredSeats = repositoryHeldSeats.findExpired(5);
		for (SeatHold seatHold : expiredSeats) {
			for (Seat seat : seatHold.getSeatsOnHold()) {
				seat.setStatus(SeatStatus.AVAILABLE);
			}
			repositoryHeldSeats.removeSeatHold(seatHold.getSeatHoldId());
		}
	}
	
	//Find And hold seats with level as optional parameter
	public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel,
			Optional<Integer> maxLevel, String customerEmail) throws IllegalArgumentException {
		removeExpiredSeats();
		Set<Seat> availableSet;
		if (minLevel.isPresent() && maxLevel.isPresent()) {
			availableSet = repositoryAvailableSeats.findAllInRange(
					minLevel.get(), maxLevel.get());
		} else if (minLevel.isPresent()) {
			availableSet = repositoryAvailableSeats.findAllInRange(
					minLevel.get(), 4);
		} else if (maxLevel.isPresent()) {
			availableSet = repositoryAvailableSeats.findAllInRange(1,
					maxLevel.get());
		} else {
			availableSet = repositoryAvailableSeats.findAll();
		}
		if (availableSet.size() < numSeats) {
			throw new IllegalArgumentException("Number of seats are more than avaiable seats");
		}
		Set<Seat> resultSet = new HashSet<>();
		int counter = 0;
		for (Seat seat : availableSet) {
			if (counter >= numSeats)
				break;
			counter++;
			seat.setStatus(SeatStatus.InProgress);
			resultSet.add(seat);
		}

		SeatHold reservedSeats = new SeatHold(customerEmail, resultSet,
				GenerateConfirmationCode.generate());
		repositoryHeldSeats.saveSeatHold(reservedSeats);
		return reservedSeats;
	}
	
	//Reserve seats: returns confirmation code
	public String reserveSeats(int seatHoldId, String customerEmail) throws IllegalArgumentException {
		removeExpiredSeats();
		if(repositoryHeldSeats.find(seatHoldId)==null){
			throw new IllegalArgumentException("The SeatHold session for this particular ID has expired");
		}
		SeatHold reserveSeats = repositoryHeldSeats.find(seatHoldId);
		for (Seat seat : reserveSeats.getSeatsOnHold()) {
			seat.setStatus(SeatStatus.OCCUPIED);
		}

		return reserveSeats.getReservationConfirmationCode();
	}

}
