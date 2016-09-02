package org.walmart_ticketService.model;

import java.util.HashSet;
import java.util.Set;

public class Venue {
	
	private Set<VenueLevel> venueLevels;

	public Venue(Set<VenueLevel> venueLevels) {
		this.venueLevels = venueLevels;
	}

	public Set<VenueLevel> getVenueLevels() {
		return venueLevels;
	}
	
	public Venue(){
		init();
	}
	
	//Initializing the Venue 
	private void init() {
		this.venueLevels = new HashSet<VenueLevel>();
		VenueLevel v1 = new VenueLevel(1, "orchastra", 25, 50, 100);
		this.venueLevels.add(v1);
		VenueLevel v2 = new VenueLevel(2, "Main", 20, 100, 75);
		this.venueLevels.add(v2);
		VenueLevel v3 = new VenueLevel(3, "Balcony 1", 15, 100, 50);
		this.venueLevels.add(v3);
		VenueLevel v4 = new VenueLevel(4, "Balcony 2", 15, 100, 40);
		this.venueLevels.add(v4);
	}

}
