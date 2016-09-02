package org.walmart_ticketService.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.walmart_ticketService.model.SeatHold;

public class RepositoryHeldSeats {

	    private Map<Integer,SeatHold> seatHeldMap;
	    
	    //Constructor
	    public RepositoryHeldSeats(){
	    	this.seatHeldMap = new HashMap<Integer, SeatHold>();
	    }
	    
	    //Find a Seat Hold by Id
	    public SeatHold find(int seatHoldId) {
	      SeatHold opt = seatHeldMap.get(seatHoldId); 
	      return opt;
	    }
	    
	    //Find the Seat Holds that have exceeded the time limit.
	    public Set<SeatHold> findExpired(int holdLimit){
	    	Set<SeatHold> expiredSet = new HashSet<>();
	    	for (SeatHold seatHold : seatHeldMap.values()) {
				if(seatHold.getTimeOfTicketHold().plusSeconds(holdLimit).isBefore(LocalDateTime.now())){
					expiredSet.add(seatHold);
				}
			}
	    	return expiredSet;
	    }
	    
	    //Save a new SeatHold to the HashMap of held seats
	    public void saveSeatHold(SeatHold saveSeatHold){
	    	if(!seatHeldMap.containsKey(saveSeatHold.getSeatHoldId())){
	    		seatHeldMap.put(saveSeatHold.getSeatHoldId(), saveSeatHold);
	    	}
	    }
	    
	    //Remove a value from the HashMap of held Seats
	    public void removeSeatHold(int seatHoldId){
	    	seatHeldMap.remove(seatHoldId);
	    }
    
}
