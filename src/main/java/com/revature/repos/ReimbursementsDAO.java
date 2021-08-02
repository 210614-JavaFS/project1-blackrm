package com.revature.repos;

import java.util.ArrayList;

import com.revature.models.Reimbursements;

public interface ReimbursementsDAO {
	
	ArrayList<Reimbursements> getAllReimbursements();
	ArrayList<Reimbursements> getAllReimbursementsByEmployee(int id);
	ArrayList<Reimbursements> getAllReimbursementsByStatus(int statusId);
	String addReimbursement(Reimbursements reimbursement);
	String updateReimbursement(int id, int resolver, int status);

}
