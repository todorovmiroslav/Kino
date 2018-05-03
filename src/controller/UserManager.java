package controller;


import java.util.Scanner;

import model.cinema.Cinema;
import model.dao.UserDao;
import model.exeptions.InvalidDataExeption;
import model.exeptions.InvalidUserExeption;
import model.user.User;
import systemCheck.SystemCheck;

public class UserManager {

	private static UserManager instance;
	private static UserDao userDao = UserDao.getInstance();
	
	private UserManager() {}
	
	public static synchronized UserManager getInstance() {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	public synchronized void registration(String username, String password, String firstName, 
													String lastName, String email, String phone) {
		try {
			User u = new User(username, password, firstName, lastName, email,  phone);
			if(!Cinema.registrationCheck(username, password)) {
				Cinema.registrateUser(u);
				//TODO make the save method take user object not Strings
				UserDao.getInstance().saveUserInDB(username,password,firstName,lastName,email,phone);
			}
		} 
		catch (InvalidUserExeption e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean signIn(String username, String password) {
		// first check for registration
		if(Cinema.registrationCheck(username, password)) {
			//User u = Cinema.getUser(username);
			//u.activeAccount = true;
			System.out.println(username + " you are logged in!");
			return true;
		}
		return false;
	}

	public void logOut(User u) {
		if(u.getAccountStatus()) {
			u.activeAccount = false;
			System.out.println(u.getUsername() + " - logout from profile!");	
		}
	}
	
	// private method.. used only when user wants to change password
	private void setNewPassword(User u, String newPassword) {
		try {
			if(SystemCheck.inputValidation(u.getUsername(), newPassword)) { 
				try {
					u.setPassword(newPassword);
				} catch (InvalidUserExeption e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} 
		catch (InvalidDataExeption e) {
			System.out.println(e.getMessage());
		}
	}
	
}
