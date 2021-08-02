package com.revature.repos;

import java.sql.SQLException;

import com.revature.models.Users;

public interface UsersDAO {
	
	Users logInUser(String username, String pwd);

}
