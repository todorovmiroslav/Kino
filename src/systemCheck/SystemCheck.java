package systemCheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.exeptions.InvalidDataExeption;

public final class SystemCheck {
	
	private SystemCheck() {
	}
	
	public static final int getRandomNum(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}

	public static final int getRandomNum(int min, int max) {
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}

	public static boolean inputValidation(String username, String password) throws InvalidDataExeption {
		if(validation(username) && validation(password)) {
			return true;
		}
		else {
			throw new InvalidDataExeption("Invalid User name or password");
		}
	}

	public static boolean validation(String text)  throws InvalidDataExeption {
		if( text != null && !text.trim().isEmpty()){
			return true;
		}
		else {
			throw new InvalidDataExeption("Invalid User name or password");
		}
	}
	
	public static boolean verifyEmail(String email) throws InvalidDataExeption {
	    if(!validation(email)) {
	    	 throw new InvalidDataExeption("Invalid e-mail");
	    }
	      
	    if(!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
	    	 throw new InvalidDataExeption("Invalid e-mail");
	    }   
	    return true;
	}
	
	public static boolean verifyName(String name) throws InvalidDataExeption {
		if(!validation(name)) {
			throw new InvalidDataExeption("Invalid Name");
	    }

	    if(!name.matches("[a-zA-Z]*")) {
			throw new InvalidDataExeption("Invalid Name");
	    }
	        
	    return true;
	}
	
	public static boolean verifyPhoneNumber(String number) throws InvalidDataExeption {
		if(!validation(number)) {
			throw new InvalidDataExeption("Invalid Phone number");
	    }

	    if(number.length() != 10 || !number.matches("[0-9]+")) {
			throw new InvalidDataExeption("Invalid Phone number");
	    }
	        
	    return true;
	}
	
	public static boolean verifyUsername(String username) throws InvalidDataExeption {

	    if(!username.matches("[A-Za-z0-9_]+")) {
			throw new InvalidDataExeption("Invalid User name");
	    }
	        
	    return true;
	}
	
	public static boolean verifyPassword(String password) throws InvalidDataExeption {
		if(!validation(password)) {
			throw new InvalidDataExeption("Invalid password");
	    }

		//^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$
	    if(!password.matches("[A-Za-z0-9_]+")) {
	    	System.out.println("Pleas enter valid password: atleast one digit, one lowercase, one uppercase and at least one symbol!");
	    	throw new InvalidDataExeption("Invalid password");
	    }
	        
	    return true;
	}
	
	public static boolean checkChanges(String change) {
		List<String> changeTypes = new ArrayList<String>(Arrays.asList("password", "name", "mail", "phone"));
		for(String s : changeTypes) {
			if(change.equals(s)) {
				return true;
			}
		}
		return false; 
	}
	
}
