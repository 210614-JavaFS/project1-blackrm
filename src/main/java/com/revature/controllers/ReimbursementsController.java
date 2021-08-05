package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.models.Reimbursements;
import com.revature.services.ReimbursementsService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReimbursementsController {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private static ReimbursementsService reimbService = new ReimbursementsService();
	

	public void addNew(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		//NOTE: in order for this to work, your JSON object's field names must match exactly your 
		//Java object fields. 
		Reimbursements reimbursement = objectMapper.readValue(body, Reimbursements.class);
		
		if(reimbService.addReimbursement(reimbursement) != null ) {
			response.setStatus(201);
		}else {
			response.setStatus(406);
		}
		
	}


	public void getMyReimbs(HttpServletResponse response, String string) throws IOException{
		// TODO Auto-generated method stub
		
		int id = Integer.valueOf(string);
		List<Reimbursements> list = reimbService.getByEmployee(id);
		
		String json = objectMapper.writeValueAsString(list);
		
		System.out.println(json);
		
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
		
	}


	public void getRequestedReimbs(HttpServletResponse response, String string) throws IOException{
		// TODO Auto-generated method stub

		int choice = Integer.valueOf(string);
		List<Reimbursements> list = new ArrayList<Reimbursements>();
		
		if(choice == 4)
			list = reimbService.getAllReimbursements();
		else
			list = reimbService.getByStatus(choice);
		
		String json = objectMapper.writeValueAsString(list);
		
		System.out.println(json);
		
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
	}


	public void updateReimb(HttpServletResponse response, String string, String string2, String string3) {
		// TODO Auto-generated method stub
		int reimbId = Integer.valueOf(string);
		int status = Integer.valueOf(string2);
		int approver = Integer.valueOf(string3);
		
		String reply = reimbService.updateReimbursement(reimbId, approver, status);
		if(reply.compareTo("Reimbursement updated") == 0)
			response.setStatus(201);
		else
			response.setStatus(406);
	}


		
}


