package model.user;

import java.util.Map;

import model.cinema.Cinema;
import model.cinema.Movie;
import model.exeptions.InvalidUserExeption;
import model.user.User;

public class Admin extends User {
	
	
	private static Admin adminInstance = null;
	private static Cinema movieTheater = Cinema.getInstance();
	
	private Admin() throws InvalidUserExeption  {
		// Admin (Name, username, password, email, phone)
		super( "admin", "admin", "Administrator", "Administrator", "admincho@abv.bg",  "0888111222");
	}
	
	public static Admin getInstance() {
		if (adminInstance == null) {
			try {
				adminInstance = new Admin();
			} catch (InvalidUserExeption e) {
				System.out.println("admin creating went wrong!");
			}
			return adminInstance;
		}
		return adminInstance;
	}
	
	public void removeUser(User u) {
		if(this.getAccountStatus()) {
			Cinema.getInstance().removeUser(u);
		}
		return;
	}
	
	public void addMovieToCinema(Movie movie) {
		if(this.getAccountStatus() && movie != null){
			Cinema.getInstance().addMovie(movie);
		}
	}
	
	public void removeMovie(Movie movie) {
		if(this.getAccountStatus() && movie != null){
			Cinema.getInstance().removeMovie(movie);
		}
	}
	
	public void addBroadcasts() {
		if(this.getAccountStatus()) {
			Cinema.getInstance().setTheBroadcasts();
		}
	}
	
	public void printBroadcasts() {
		if(this.getAccountStatus()) {
			Cinema.getInstance().printBroadcasts();
		}
	}
	
	public void removeBroadcast() {
		// TODO
	}
	
	public void cancelUserReservation() {
		// TODO
	}
	
	public void cancelAllReservations() {
		// TODO
	}
}