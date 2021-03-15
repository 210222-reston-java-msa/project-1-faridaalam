package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ers.models.User;
import com.ers.utils.ConnectionUtil;

public class ErsDaoImpl implements ErsDao {
	
	private static Logger log = Logger.getLogger(ErsDaoImpl.class);



	@Override
	public boolean update(User user) {
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "UPDATE users SET firstname= ?, lastName = ?, pass = ? WHERE user_id =?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getPassword());
			stmt.setInt(4, user.getId());
			
			int result = stmt.executeUpdate();
			log.info("User updated" + user.getId());
			return true;
				
			
			

		} catch (SQLException ex) {
			log.warn("Unable to update user: " + user.getEmail() + "\n"+ ex);
			ex.printStackTrace();

		}
		
		
		return false;
	}

	@Override
	public User login(String email, String password) {

		User user = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM users where email=? and pass=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("user_id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String em = rs.getString("email");
				String pass = rs.getString("pass");
				int role = rs.getInt("role");
				
				user = new User(id, firstname, lastname, em, pass , role);
				
				log.info("Login: " +em);
			}
			

		} catch (SQLException ex) {
			log.warn("Unable to retrieve user: " + email + "\n"+ ex);
			ex.printStackTrace();

		}
		
		
		return user;
	}

	@Override
	public boolean userExist(User u) {
		
		String email = u.getEmail();
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM users where email=?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				log.info("userExist: " +email);
				return true;
			}
			

		} catch (SQLException ex) {
			log.warn("Unable to retrieve user: " + email + "\n"+ ex);
			ex.printStackTrace();

		}
		return false;
	}

	@Override
	public User signUp(User u) {
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "INSERT INTO ers.users\n"
					+ "( pass, email, \"role\", firstname, lastname) "
					+ "VALUES(?, ?, ?, ?, ?) RETURNING user_id ;";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, u.getPassword());
			stmt.setString(2, u.getEmail());
			stmt.setInt(3, u.getRole());
			stmt.setString(4, u.getFirstName());
			stmt.setString(5, u.getLastName());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				u.setId(rs.getInt("user_id"));
				
				return u;
				
			}
			

		} catch (SQLException ex) {
			log.warn("Unable to add user: " + u.getEmail() + "\n"+ ex);
			ex.printStackTrace();

		}
		
		
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
	
		User user = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM users where email=? ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("user_id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String em = rs.getString("email");
				String pass = rs.getString("pass");
				int role = rs.getInt("role");
				
				user = new User(id, firstname, lastname, em, pass , role);
				
				log.info("find user by email: " +em);
			}
			

		} catch (SQLException ex) {
			log.warn("Unable to retrieve user: " + email + "\n"+ ex);
			ex.printStackTrace();

		}
		
		
		return user;
	}

	@Override
	public User findUserById(int mgrId) {
		User user = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT * FROM users where user_id=? ";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, mgrId);

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("user_id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String em = rs.getString("email");
				String pass = rs.getString("pass");
				int role = rs.getInt("role");
				
				user = new User(id, firstname, lastname, em, pass , role);
				
				log.info("find user by Id: " +em);
			}
			

		} catch (SQLException ex) {
			log.warn("Unable to retrieve user by Id: " + mgrId + "\n"+ ex);
			ex.printStackTrace();

		}
		
		
		return user;
	}
}
