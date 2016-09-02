package org.walmart_ticketService.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VenueLevel {

	private int levelId;
	private String levelName;
	private int numberOfRows;
	private int numberOfSeatsPerRow;
	private Map<Integer, Set<Seat>> setOfSeats;
	private double price;
	
	public static int score = 0;

	public VenueLevel(int levelId, String levelName, int numberOfRows, int numberOfSeatsPerRow, double price) {
		this.levelId = levelId;
		this.levelName = levelName;
		this.numberOfRows = numberOfRows;
		this.numberOfSeatsPerRow = numberOfSeatsPerRow;
		this.price = price;
		this.setOfSeats = new HashMap<Integer, Set<Seat>>();
		init();
	}
	//Initialize the venue levels 
	private void init() {
		
		Seat seat;
		HashSet<Seat> seats;
		for (int i = 0; i < this.numberOfRows; i++) {
			 seats = new HashSet<>();
			
			for (int j = 0; j < this.numberOfSeatsPerRow; j++) {
				 seat = new Seat(i, j, SeatStatus.AVAILABLE, score,levelId);
				seats.add(seat);
				//Score calculation is dependent on the proximity of the seat to the stage
				//Level1:Row1:Seat1 is the "best" possible seat 
				//Last-Level:Last-Row:Last-Seat is the "worst" possible seat
				score++;
			}
			setOfSeats.put(i, seats);
		}

	}
	
	public Set<Seat> getSeatsForRow(int row){
		return this.getSetOfSeats().get(row);
	}
	public int getLevelId() {
		return levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public int getNumberOfSeatsPerRow() {
		return numberOfSeatsPerRow;
	}

	public int getTotalSeats() {
		return numberOfRows * numberOfSeatsPerRow;
	}

	public Map<Integer, Set<Seat>> getSetOfSeats() {
		return setOfSeats;
	}

	public double getPrice() {
		return price;
	}

}
