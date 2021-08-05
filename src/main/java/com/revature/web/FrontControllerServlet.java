package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.ReimbursementsController;
import com.revature.controllers.UserController;

public class FrontControllerServlet extends HttpServlet {
	
	private ReimbursementsController reimbursementsController = new ReimbursementsController();
	private UserController userController = new UserController();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		
		
		response.setStatus(404);

		final String URL = request.getRequestURI().replace("/project1/", "");


		String[] UrlSections = URL.split("/");

		switch (UrlSections[0]) {
		case "login":
			if(UrlSections.length == 3)
				userController.logIn(response, UrlSections[1], UrlSections[2]);
			break;
		case "employee":
			if(UrlSections.length == 1) {
				reimbursementsController.addNew(request, response);
			} else if(UrlSections.length == 2)
				reimbursementsController.getMyReimbs(response, UrlSections[1]);
			break;
		case "finance":
			if(UrlSections.length == 2)
				reimbursementsController.getRequestedReimbs(response, UrlSections[1]);
			else
				reimbursementsController.updateReimb(response, UrlSections[1], UrlSections[2], UrlSections[3]);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request,response);
	}
	
}
