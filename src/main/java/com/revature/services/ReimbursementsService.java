package com.revature.services;

import java.util.ArrayList;

import com.revature.models.Reimbursements;
import com.revature.repos.ReimbursementsDAO;
import com.revature.repos.ReimbursementsDAOImpl;

public class ReimbursementsService {
	
	private static ReimbursementsDAO reimbursementsDAO = new ReimbursementsDAOImpl();
	
	public ArrayList<Reimbursements> getAllReimbursements() {
		return reimbursementsDAO.getAllReimbursements();
	}
	
	public ArrayList<Reimbursements> getByEmployee(int id) {
		return reimbursementsDAO.getAllReimbursementsByEmployee(id);
	}
	
	public ArrayList<Reimbursements> getByStatus(int statusId) {
		return reimbursementsDAO.getAllReimbursementsByStatus(statusId);
	}
	
	public String addReimbursement(Reimbursements reimbursement) {
		return reimbursementsDAO.addReimbursement(reimbursement);
	}
	
	public String updateReimbursement(int id, int resolver, int status) {
		return reimbursementsDAO.updateReimbursement(id, resolver, status);
	}
}
