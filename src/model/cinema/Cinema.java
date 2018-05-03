package model.cinema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StreamCorruptedException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import model.user.User;
import model.dao.UserDao;
import model.user.Admin;

public class Cinema {

	// Singleton class
	private static final int MAX_MOVIE_FOR_THE_WEEK = 4;
	private static final int ALL_HALLS = 4;
	private static final String PATH_FOR_MOVIES = "Movies.txt";
	private static Cinema instance;
	private String name;
	private String address;
	private static TreeMap<String, User> users = new TreeMap<>(); // Collection <username, user>
	private Set<Watchable> allMovies = new HashSet<>();
	private LinkedHashSet<Hall> halls = new LinkedHashSet<>();
	private TreeSet<Broadcast> broadcasts = new TreeSet<>();
	//private Admin admin = Admin.getInstance();
	
	private Cinema() {
		this.name = "Botevgrad Movie Theater";
		this.address = "Cinema Str 7";
		
		for (int i = 1; i <= ALL_HALLS; i++) {
			this.halls.add(new Hall(i, this));
		}
		
		//add admin to cinema:
		users.put("admin", Admin.getInstance());
	}

	public static Cinema getInstance() {
		if (instance == null) {
			instance = new Cinema();
			return instance;
		}
		return instance;
	}
	
	public void setTheBroadcasts() {
		int i = 0;
		ArrayList<Hall> halls = new ArrayList<>(this.halls);
		
		//Get all the movies and shuffle them so we can get a random movie every week
		ArrayList<Watchable> movies = new ArrayList<>(this.allMovies);
		Collections.shuffle(movies);
		for (Watchable w : movies) {
			this.broadcasts.add(new Broadcast(w, LocalTime.of(10, 00), halls.get(i)));
			this.broadcasts.add(new Broadcast(w, LocalTime.of(14, 00), halls.get(i)));
			this.broadcasts.add(new Broadcast(w, LocalTime.of(19, 00), halls.get(i)));
			this.broadcasts.add(new Broadcast(w, LocalTime.of(22, 30), halls.get(i)));
			if(Cinema.MAX_MOVIE_FOR_THE_WEEK == i) {
				break;
			}
			i++;
		}
	}

	public void printBroadcasts() {
		System.out.println("#########Broadcast for the week in cinema " + this.name + "########");
		System.out.println();
		for (Broadcast b : this.broadcasts) {
			System.out.println(b.getMovie().getName() + " from " + b.getProjectionTime() + " in hall: " + b.getProjectionHall().getNumber());
		}
		System.out.println();
		System.out.println();
	}

	public Reservation checkReservation(Broadcast b, int places) {
		// check is there enough places for this broadcast
		if (b.getPlaces() < places) {
			// else if free places are not enough --> message
			System.out.println("Sorry, not eneug free sits for this broadcast.");
		}
		// if there are enough places --> return reservation for this broadcast with places
		return new Reservation(b, places);
	}

	public void addMovie(Movie movie) {
		boolean containsMovie = this.allMovies.contains(movie);
		if (movie != null) {
			this.allMovies.add(movie);
		}
		if(!containsMovie) {
			addMovieToArchive(movie);	
		}
	}

	private void addMovieToArchive(Movie m) {
		try {
			//Add movies to file
			File movieFile = new File(Cinema.PATH_FOR_MOVIES);
			if(!movieFile.exists()) {
				movieFile.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(movieFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.allMovies);
			oos.flush();
			oos.close();
		
		}catch (IOException e) {
			System.out.println("Somethin is wrong with the movie arhive file on output!!");
		}
	}
	
	public void removeMovie(Movie movie) {
		if (movie != null) {
			this.allMovies.remove(movie);
		}
	}

	public Set<Broadcast> getBroadcasts() {
		return Collections.unmodifiableSet(this.broadcasts);
	}

	public static boolean registrationCheck(String inputUsername, String inputPassword) {
		//check the collection for the user
		/*if(users.containsKey(inputUsername)) {
			if(users.get(inputUsername).getPassword().equals(inputPassword)) {
				return true;
			}
		}*/
		//check the DB for the user
		if(UserDao.getInstance().checkForUser(inputUsername,inputPassword)) {
			return true;
		}
		
		return false;
	}

	public static void registrateUser(User user) {
		users.put(user.getUsername(), user);
	}

	public String getName() {
		return name;
	}
	
	public void showAllUsers() {
		for(User u : users.values()) {
			u.showUserInfo();
		}
	}

	public void removeUser(User u) {
		System.out.println("User " + u.getUsername() + " will be removed!");
		users.remove(u.getUsername());
	}
	
	public void showAllMovies(){
		for(Watchable w : allMovies) {
			System.out.println(w);
		}
	}

	public static User getUser(String username) {
		return users.get(username);
	}
	
}
