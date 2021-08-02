package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Users;
import com.revature.services.UsersService;

public class UserController {
	
	private static UsersService usersService = new UsersService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void logIn(HttpServletResponse response, String username, String password) throws IOException {
		Users user = usersService.logInUser(username.replace("%20", " "), password.replace("%20", " "));
		String json = objectMapper.writeValueAsString(user);
		response.getWriter().print(json);
		response.setStatus(200);
	}

}
