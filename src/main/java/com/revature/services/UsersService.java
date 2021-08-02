package com.revature.services;

import com.revature.models.Users;
import com.revature.repos.UsersDAO;
import com.revature.repos.UsersDAOImpl;

public class UsersService {

	private static UsersDAO usersDAO = new UsersDAOImpl();
	
	public Users logInUser(String username, String password) {
		return usersDAO.logInUser(username, password);
	}
	
}
