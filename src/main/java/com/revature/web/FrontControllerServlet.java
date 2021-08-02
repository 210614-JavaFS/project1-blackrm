package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.ReimbursementsController;
import com.revature.controllers.UserController;
import com.revature.utils.JDBCConnection;

public class FrontControllerServlet extends HttpServlet {
	
	private ReimbursementsController reimbursementsController = new ReimbursementsController();
	private UserController userController = new UserController();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		
//		JDBCConnection.makeConnection();
		
		response.setStatus(404);

		final String URL = request.getRequestURI().replace("/project1/", "");

		System.out.println(URL);

		String[] UrlSections = URL.split("/");
		
		for(int k = 0; k < UrlSections.length; k++)
			System.out.println(UrlSections[k]);

		switch (UrlSections[0]) {
		case "login":
			userController.logIn(response, UrlSections[0], UrlSections[1]);
			break;
		
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
}
