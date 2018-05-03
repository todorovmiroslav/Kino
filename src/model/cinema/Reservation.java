package model.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.plaf.BorderUIResource;

public class Reservation {
	
	private Broadcast broadcast;
	private int places;
	private boolean canceled;
	
	public Reservation(Broadcast b , int places) {
		this.places = places;
		this.broadcast = b;
	}
	
	public void cancel() {
		this.canceled = true;
	}
	
	
	
}
