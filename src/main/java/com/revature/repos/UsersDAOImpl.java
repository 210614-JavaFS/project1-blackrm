package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.repos.UsersDAOImpl;
import com.revature.models.Users;

import com.revature.utils.*;

public class UsersDAOImpl implements UsersDAO{
	
	private static Logger logger = LoggerFactory.getLogger(UsersDAOImpl.class);

	@Override
	public Users logInUser(String username, String pwd) {
		// TODO Auto-generated method stub
		
		
		Users user = new Users();
		try(Connection conn = JDBCConnection.makeConnection();) {
			PreparedStatement pstmt = conn.prepareStatement("select ers_users_id, ers_username, ers_password, user_role_id from ers_users where ers_username = ? and ers_password = crypt(?, ers_password)");
			logger.debug("logging in");
			pstmt.setString(1, username);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setRoleId(rs.getInt(4));;
			}
		} catch (SQLException e) {
			logger.error("couldn't log in", e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
