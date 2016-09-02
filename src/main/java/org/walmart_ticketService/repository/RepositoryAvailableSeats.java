package org.walmart_ticketService.repository;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.walmart_ticketService.model.Seat;
import org.walmart_ticketService.model.SeatStatus;
import org.walmart_ticketService.model.Venue;

public class RepositoryAvailableSeats {
    private Set<Seat> allSeats;
    private Venue venue;
    
    //Constructor
	public RepositoryAvailableSeats( Venue venue) {
		this.allSeats = new TreeSet<Seat>(sortedSeatComparator);
		this.venue = venue;
		initializeSeats();
	}
	
	//Initialize Seats
	private void initializeSeats() {
		venue.getVenueLevels().forEach(level ->{
			for (int i = 0; i < level.getNumberOfRows(); i++) {
                allSeats.addAll(level.getSeatsForRow(i));  
			}
		});
	}

	//Find all available Seats
	public Set<Seat> findAll() {
		Set<Seat> avaiableSet = new TreeSet<Seat>(sortedSeatComparator);
		for (Seat seat : allSeats) {
			if(seat.getSeatStatus()== SeatStatus.AVAILABLE){
				avaiableSet.add(seat);
			}
		}
		return avaiableSet;
	}
	
	//Find all available seats for a particular level
	public Set<Seat> findAllLevel(int level){
		Set<Seat> currentSeat = new TreeSet<Seat>(sortedSeatComparator);
		for (Seat seat : allSeats) {
			if(seat.getLevel()==level && seat.getSeatStatus()== SeatStatus.AVAILABLE ){
				currentSeat.add(seat);
			}
		}
		return currentSeat;
	}
	
	//Find all available seats for a given range of levels
	public Set<Seat> findAllInRange(int minLevel, int maxLevel){
		Set<Seat> currentSeat = new TreeSet<Seat>(sortedSeatComparator);
		for (Seat seat : allSeats ) {
			if(seat.getLevel()>=minLevel && seat.getLevel()<=maxLevel && seat.getSeatStatus()== SeatStatus.AVAILABLE){
				currentSeat.add(seat);
			}
		}
		return currentSeat;
	}
	
	//Seat Comparator: Helper for TreeSet to sort the seats based on Seat Score
	public static Comparator<Seat> sortedSeatComparator = new Comparator<Seat>() {
		
		@Override
		public int compare(Seat s1, Seat s2) {
			// TODO Auto-generated method stub
			return s1.getScore()-s2.getScore();
		}
	};
	
	
}

