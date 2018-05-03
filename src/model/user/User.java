package model.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import model.exeptions.InvalidDataExeption;
import model.exeptions.InvalidUserExeption;
import systemCheck.SystemCheck;
import model.cinema.Broadcast;
import model.cinema.Cinema;
import model.cinema.Movie;
import model.cinema.Reservation;
import model.cinema.Watchable;

public class User {
	
	protected int id;
	protected String firstname; 
	protected String lastname; 
	protected String username;
	protected String password;
	protected String email;
	protected String phone;
	public boolean activeAccount;
	private Set<Watchable> favoriteList = new HashSet<Watchable>();
	private List<Reservation> reservations = new ArrayList<>();
	
	public User(String username,String password,String firstname,String lastname,String email, String phoneNumber) throws InvalidUserExeption {
		setUsername(username);
		setPassword(password);
		setFirstName(firstname);
		setLastName(lastname);
		setEmail(email);
		setPhone(phoneNumber);
	}
	
	public User(int id,String username,String password, String firstname,String lastname, String email, String phoneNumber) throws InvalidUserExeption {
		this(username, password, firstname, lastname, email, phoneNumber);
		setId(id);
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	// getters:
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	public boolean getAccountStatus() {
		return this.activeAccount;
	}
	
	// setters:
	public void setUsername(String username) throws InvalidUserExeption {
		try {
			if(SystemCheck.verifyUsername(username)){
				this.username = username;
			}
		} catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}

	public void setPassword(String password) throws InvalidUserExeption {
		try {
			if(SystemCheck.verifyPassword(password)){
				this.password = password;
			}
		} catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}
	
	protected void setFirstName(String name) throws InvalidUserExeption {
		try {
			if(SystemCheck.validation(name)) {
				this.firstname = name;
			}
		}
		catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}

	
	protected void setLastName(String name) throws InvalidUserExeption {
		try {
			if(SystemCheck.validation(name)) {
				this.lastname = name;
			}
		}
		catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}
	
	protected void setEmail(String email) throws InvalidUserExeption{
		try {
			if(SystemCheck.verifyEmail(email)){
				this.email = email;
			}
		}
		catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
		
	}

	protected void setPhone(String phone) throws InvalidUserExeption{
		try {
			if(SystemCheck.verifyPhoneNumber(phone)){
				this.phone = phone;
			}
		} 
		catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}
	
	
	
	public void addMovieToFavoriteList(Movie movie) {
		if(movie != null){
			this.favoriteList.add(movie);
		}
	}
	
	public void rateMovie(Movie m, int rate) {
		m.setRating(rate);
	}
	
	public void makeReservation(Cinema movieTheater, int places) {
		if(this.activeAccount) {
			ArrayList<Broadcast> broad = new ArrayList<Broadcast>(movieTheater.getBroadcasts());
			if(!broad.isEmpty()) {
				Broadcast b = broad.get(SystemCheck.getRandomNum(broad.size()-1));
				System.out.println(this.username + ": I'm making reservation for " + b.getMovie().getName() + " for " +b.getProjectionTime() + " in hall " + b.getProjectionHall().getNumber() + " and " + places + " sits");
				Reservation reserve = movieTheater.checkReservation(b,places);
				if(reserve != null) {
					System.out.println("Reservation is complete!");
					//Rate the movie
					this.rateMovie(b.getMovie(), SystemCheck.getRandomNum(2, 6));
					//Set the movie to favorite
					if(new Random().nextBoolean()) {
						this.addMovieToFavoriteList(b.getMovie());
					}
					this.reservations.add(reserve);
				}
			}
			else {
				System.out.println("Sorry, no broadcasts.. we are closed.");
			}
		}
		else {
			System.out.println("You have to SignIn to make reservation!");
		}
	}
	
	
	public void cancelLastReservation() {
		if(this.activeAccount) {
			if(this.reservations.size() != 0) {
				this.reservations.get(this.reservations.size()-1).cancel();
				this.reservations.remove(this.reservations.size()-1);	
				System.out.println(this.username + " cancel my last reservation.");
			}else {
				System.out.println("No reservation to cancel!");
			}
		}else {
			System.out.println("You have to SignIn to cancel reservation!");
		}
	}
	
	public void reciveTicket(Reservation t) {
		if(t != null) {
			this.reservations.add(t);
		}
	}
	
	public void profileChanges() {
		if(this.getAccountStatus()) {
			Scanner sc = new Scanner(System.in);
			System.out.println(this.username + " wants to make some profile changes: ");
			System.out.println("to change your password type: password" );
			System.out.println("to change your name type: name");
			System.out.println("to change your e-mail: mail" );
			System.out.println("to change your phorne type: phone" );
			
			try{
				String change = sc.nextLine();
				if(SystemCheck.checkChanges(change)) {
					switch (change) {	
						case "password":
						System.out.println("Please enter new password: ");
						String newPassword = sc.nextLine();
						this.setPassword(newPassword);
						break;
						case "names":
							System.out.println("Please enter first name: ");
							String newFirstName = sc.nextLine();
							this.setFirstName(newFirstName);
							System.out.println("Please enter first name: ");
							String newLastName = sc.nextLine();
							this.setFirstName(newLastName);
							break;
						case "mail":
							System.out.println("Please enter new e-mail: ");
							String newMail = sc.nextLine();
							this.setEmail(newMail);
							break;
						case "phone":
							System.out.println("Please enter new phone number: ");
							String newPhone = sc.nextLine();
							this.setPhone(newPhone);
							break;
						default:
							break;
					}
				}
			}
			catch (InvalidUserExeption e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public Set<Watchable> getFavoriteList() {
		return Collections.unmodifiableSet(favoriteList);
	}
	
	public void showUserInfo() {
		System.out.println("\n=====User information=====");
		System.out.println("names: " + this.firstname + " " + this.lastname + 
						  "\nusername: " + this.username + 
						  "\ne-mail: "+ this.email + 
						  "\nphone: "+ this.phone);
		System.out.println("==========================");
	}
}
