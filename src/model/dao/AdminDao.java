package model.dao;

import java.sql.Connection;

import dataBase.DBManager;

public class AdminDao {

	private static AdminDao instance;
	private Connection connection;
	
	private AdminDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public synchronized static AdminDao getInstance() {
		if(instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
}
