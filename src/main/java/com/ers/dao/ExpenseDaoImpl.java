package com.ers.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ers.models.Expense;
import com.ers.models.User;
import com.ers.utils.ConnectionUtil;

public class ExpenseDaoImpl implements ExpenseDao {
	
	private static Logger log = Logger.getLogger(ExpenseDaoImpl.class);


	@Override
	public int insert(int id, Expense expense) {
	
		int expId = 0;

		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "INSERT INTO expense (amount, user_comment, \"type\", status, user_id, \"date\", image , imgadded) "
					+ "VALUES (?,?,?,?,?,?,?,?) RETURNING id";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setBigDecimal(1, expense.getAmount());
			stmt.setString(2, expense.getNote());
			stmt.setInt(3, expense.getType());
			stmt.setInt(4, expense.getStatus());
			stmt.setInt(5, id);
			stmt.setObject(6, expense.getTime());
			stmt.setBytes(7, expense.getImage());
			stmt.setBoolean(8, expense.isImgAdded());

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				expId = rs.getInt(1);
				
				
				log.info("Expense added, user ID: " + id + " expense ID" + expId);
			}
			

		} catch (SQLException ex) {
			log.warn("Unable to add expense" + ex);
			ex.printStackTrace();

		}
		
		
		
		
		
		return expId;
	}


	@Override
	public List<Expense> getAllExp(User user) {
		
		List<Expense> expList = new ArrayList<Expense>();
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT id, amount, \"date\", user_comment, \"type\" ,status, imgadded\n"
					+ " FROM expense where  user_id =? ORDER BY \"date\" DESC\n";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, user.getId());
			
			ResultSet rs = stmt.executeQuery();
			
			Expense exp ;
			
			
			while (rs.next()) {
				
				exp = new Expense (
				rs.getInt(1), rs.getBigDecimal(2),rs.getObject(3, LocalDateTime.class), 
				rs.getString(4), rs.getInt(5),rs.getInt(6),rs.getBoolean(7));
				
				exp.setUserId(user.getId());
				expList.add(exp);
									
			}
			
			log.info("All expenses retrieved, user ID: " + user.getId());

		} catch (SQLException ex) {
			log.warn("Unable to retrieve expenses." + ex);
			ex.printStackTrace();
		}
		
		return expList;
		
		}


	@Override
	public byte[] getExpImg(int expId) {
		
		byte[] img =null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT image FROM expense e2 WHERE id = ?";
					
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1,expId);
			
			System.out.println(expId);
			ResultSet rs = stmt.executeQuery();
			
						
			while (rs.next()) {
				
				img= rs.getBytes(1);
				
									
			}
			
			log.info("Expense image retrieved from db " + expId );

		} catch (SQLException ex) {
			log.warn("Error Loading img from Database" + expId);
			ex.printStackTrace();
		}
		
		
		return img;
	}


	@Override
	public List<Expense> getAllExpMgr() {
		
		List<Expense> expList = new ArrayList<Expense>();
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = " SELECT id, user_id, mgr_id, amount, \"date\", user_comment, \"type\" ,status, imgadded "
					+ " FROM expense  ORDER BY \"date\" DESC";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			
			ResultSet rs = stmt.executeQuery();
			
			Expense exp ;
			
			
			while (rs.next()) {
				
				exp = new Expense (
				rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getBigDecimal(4),rs.getObject(5, LocalDateTime.class), 
				rs.getString(6), rs.getInt(7),rs.getInt(8),rs.getBoolean(9));
				
				
				expList.add(exp);
									
			}
			
			log.info("All expenses retrieved, For All Users ");

		} catch (SQLException ex) {
			log.warn("Unable to retrieve expenses."+ ex);
			ex.printStackTrace();
		}
		
		return expList;
		
		}


	@Override
	public void approve(String expId, int mgrId) {
	
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "UPDATE expense SET status= 2, mgr_Id = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, mgrId);
			stmt.setInt(2, Integer.valueOf(expId));
			System.out.println(mgrId);
			
			int result = stmt.executeUpdate();
			log.info("Expense approved" + expId);
			return;
				
			
			

		} catch (SQLException ex) {
			log.warn("Unable to approve expense: " + expId + "\n"+ ex);
			ex.printStackTrace();

		}
		
	}


	@Override
	public void deny(String expId, int mgrId) {
		// TODO Auto-generated method stub
		

		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "UPDATE expense SET status= 3, mgr_Id = ? WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, mgrId);
			stmt.setInt(2, Integer.valueOf(expId));
			
			int result = stmt.executeUpdate();
			log.info("Expense denied" + expId);
			return;
				
			
			

		} catch (SQLException ex) {
			log.warn("Unable to deny expense: " + expId + "\n"+ ex);
			ex.printStackTrace();

		}
		
	}
	
	
	
	
}