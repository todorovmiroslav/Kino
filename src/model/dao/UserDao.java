package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dataBase.DBManager;


public class UserDao {

	private static UserDao instance;
	private Connection connection;
	
	private UserDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public synchronized static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	public void saveUserInDB(String username, String password, String firstName, 
			String lastName, String email, String phone) {
		System.out.println("going to save db");
		try (PreparedStatement ps = connection
				.prepareStatement("INSERT INTO `movies`.`users` (first_name,last_name,user_name,password,email,is_Admin,phone) VALUES (?,?,?,?,?,?,?)");) {
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, email);
			ps.setBoolean(6, false);
			ps.setString(7, phone);
			
			ps.executeUpdate();
			System.out.println("done");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean checkForUser(String username, String password) {
		try (PreparedStatement ps = connection
				.prepareStatement("SELECT user_name, password FROM `movies`.`users` WHERE user_name = ? AND password = ? ;");) {
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		//System.out.println(rs.getMetaData());
		if(rs.next()) {
			System.out.println("true");
			return true;
		}
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
}
