package org.walmart_ticketService.model;

import org.walmart_ticketService.model.Seat;

public class Seat {
	private int row;
	private int Seatnumber;
	private SeatStatus status = SeatStatus.AVAILABLE;
	private int score;
	private int level;
	
	public Seat(int row, int seatnumber,SeatStatus status, int score,int level) {
		this.row = row;
		this.Seatnumber = seatnumber;
		this.status = status;
		this.score = score;
		this.level = level;
	}
	
	public Seat(int row, int seatnumber,SeatStatus status) {
		this.row = row;
		this.Seatnumber = seatnumber;
		this.status = status;
	}
	
	public Seat(int row, int seatnumber, int score) {
		this.row = row;
		this.Seatnumber = seatnumber;
		this.score = score;
	}
	
	public Seat(int row, int seatnumber) {
		this.row = row;
		this.Seatnumber = seatnumber;
	}
	
	public Seat(int seatnumber) {
		this.Seatnumber = seatnumber;
	}

	public int getLevel(){
		return level;
	}
	
	public int getRow() {
		return row;
	}

	public int getSeatnumber() {
		return Seatnumber;
	}

	public SeatStatus getSeatStatus() {
		return status;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setStatus(SeatStatus updatedStatus){
		this.status = updatedStatus;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+"row: "+this.row+" seatbr: "+Seatnumber+" score: "+score+" level: "+level+"]";
	}

}
