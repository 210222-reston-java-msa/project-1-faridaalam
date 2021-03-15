package com.ers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ers.models.Expense;
import com.ers.utils.ConnectionUtil;

public class Driver {

	ExpenseDao expDao = new ExpenseDaoImpl();
	
	public static void main(String[] args) {
		
	Expense exp = new Expense();
		
		try {
			Connection conn = ConnectionUtil.getConnection();
			
			String sql = "SELECT id, amount,image FROM expense e Where id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, 6);
			
			ResultSet rs = stmt.executeQuery();
			
			
			
			
			while (rs.next()) {
				
				System.out.println(rs.getBytes(3));
					exp.setImage(rs.getBytes(3));
					System.out.println(exp.getImage());
			}
			

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
	}
}
