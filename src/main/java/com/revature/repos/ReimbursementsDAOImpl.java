package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Reimbursements;
import com.revature.utils.JDBCConnection;

public class ReimbursementsDAOImpl implements ReimbursementsDAO {
	
//	static Connection conn = JDBCConnection.makeConnection();
	private static Logger logger = LoggerFactory.getLogger(UsersDAOImpl.class);

	@Override
	public ArrayList<Reimbursements> getAllReimbursements() {
		// TODO Auto-generated method stub
		ArrayList<Reimbursements> reimbs = new ArrayList<Reimbursements>();
		
		try(Connection conn = JDBCConnection.makeConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("select reimb_id, "
					+ "reimb_amount, reimb_submitted, reimb_resolved, "
					+ "reimb_description, reimb_author, reimb_resolver, "
					+ "reimb_status_id, reimb_type_id from ers_reimbursement");
			logger.debug("finding all reimbursements");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Reimbursements reimb = new Reimbursements();
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getDouble(2));
				reimb.setSubmitted(rs.getDate(3));
				reimb.setResolved(rs.getDate(4));
				reimb.setDescription(rs.getString(5));
				reimb.setAuthor(rs.getInt(6));
				reimb.setResolver(rs.getInt(7));
				reimb.setStatusId(rs.getInt(8));
				reimb.setTypeId(rs.getInt(9));
				reimbs.add(reimb);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("reimbursements could not be found", e);
			e.printStackTrace();
		}
		return reimbs;
	}

	@Override
	public ArrayList<Reimbursements> getAllReimbursementsByEmployee(int id) {
		// TODO Auto-generated method stub
		ArrayList<Reimbursements> reimbs = new ArrayList<Reimbursements>();
		
		try(Connection conn = JDBCConnection.makeConnection()) {
			PreparedStatement pstmt = conn.prepareStatement("select reimb_id, "
					+ "reimb_amount, reimb_submitted, reimb_resolved, "
					+ "reimb_description, reimb_author, reimb_resolver, "
					+ "reimb_status_id, reimb_type_id from ers_reimbursement "
					+ "where reimb_author = ?");
			logger.debug("finding all reimbursements for " + id);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Reimbursements reimb = new Reimbursements();
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getDouble(2));
				reimb.setSubmitted(rs.getDate(3));
				reimb.setResolved(rs.getDate(4));
				reimb.setDescription(rs.getString(5));
				reimb.setAuthor(rs.getInt(6));
				reimb.setResolver(rs.getInt(7));
				reimb.setStatusId(rs.getInt(8));
				reimb.setTypeId(rs.getInt(9));
				reimbs.add(reimb);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("reimbursements could not be found for " + id, e);
			e.printStackTrace();
		}
		return reimbs;	
	}

	@Override
	public ArrayList<Reimbursements> getAllReimbursementsByStatus(int statusId) {
		// TODO Auto-generated method stub
		ArrayList<Reimbursements> reimbs = new ArrayList<Reimbursements>();
		
		try (Connection conn = JDBCConnection.makeConnection()){
			PreparedStatement pstmt = conn.prepareStatement("select reimb_id, "
					+ "reimb_amount, reimb_submitted, reimb_resolved, "
					+ "reimb_description, reimb_author, reimb_resolver, "
					+ "reimb_status_id, reimb_type_id from ers_reimbursement "
					+ "where reimb_status_id = ?");
			logger.debug("finding all reimbursements for " + statusId);
			pstmt.setInt(1, statusId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Reimbursements reimb = new Reimbursements();
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getDouble(2));
				reimb.setSubmitted(rs.getDate(3));
				reimb.setResolved(rs.getDate(4));
				reimb.setDescription(rs.getString(5));
				reimb.setAuthor(rs.getInt(6));
				reimb.setResolver(rs.getInt(7));
				reimb.setStatusId(rs.getInt(8));
				reimb.setTypeId(rs.getInt(9));
				reimbs.add(reimb);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("reimbursements could not be found for " + statusId, e);
			e.printStackTrace();
		}
		return reimbs;		}

	@Override
	public String addReimbursement(Reimbursements reimbursement) {
		// TODO Auto-generated method stub
		String myMessage = "";
		Date date = new java.util.Date();
		reimbursement.setSubmitted(date);
		reimbursement.setStatusId(1);
		try (Connection conn = JDBCConnection.makeConnection()){
			PreparedStatement pstmt = conn.prepareStatement("insert into "
					+ "ers_reimbursement(reimb_amount, reimb_submitted, "
					+ "reimb_description, reimb_author, reimb_status_id, "
					+ "reimb_type_id) values (?,?,?,?,?,?)");
			logger.debug("adding a reimbursement");
			pstmt.setDouble(1, reimbursement.getAmount());
			pstmt.setDate(2, (java.sql.Date) reimbursement.getSubmitted());
			pstmt.setString(3, reimbursement.getDescription());
			pstmt.setInt(4, reimbursement.getAuthor());
			pstmt.setInt(5, reimbursement.getStatusId());
			pstmt.setInt(6, reimbursement.getTypeId());
			pstmt.executeUpdate();
			myMessage = "Reimbursement added";
		} catch (SQLException e) {
			myMessage = "Reimbursement could not be added";
			logger.error("new reimbursement could not be added", e);
			e.printStackTrace();
		}
		return myMessage;
	}

	@Override
	public String updateReimbursement(int id, int resolver, int status) {
		// TODO Auto-generated method stub
		String myMessage = "";
		Date date = new java.util.Date();
		try (Connection conn = JDBCConnection.makeConnection()){
			PreparedStatement pstmt = conn.prepareStatement("update "
					+ "ers_reimbursement set reimb_resolved = ?, "
					+ "reimb_resolver = ?, reimb_status_id = ? "
					+ "where reimb_id = ?");
			logger.debug("updating reimbursement " + id);
			pstmt.setDate(1, (java.sql.Date) date);
			pstmt.setInt(2, resolver);
			pstmt.setInt(3, status);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
			myMessage = "Reimbursement " + id + " updated";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("reimbursement could not be updated", e);
			myMessage = "Reimbursement " + id + " not successfully updated";
			e.printStackTrace();
		}
		return myMessage;
	}

}
