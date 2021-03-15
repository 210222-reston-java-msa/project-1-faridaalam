package com.ers.services;


import java.util.List;

import com.ers.dao.ExpenseDao;
import com.ers.dao.ExpenseDaoImpl;
import com.ers.models.Expense;
import com.ers.models.User;


public class ExpenseService {
	
	public static ExpenseDao expDao = new ExpenseDaoImpl();

	public static int insert(int id , Expense expense) {
		return expDao.insert(id, expense);
	}

	public static List<Expense> getAllExp(User user) {
		
		return expDao.getAllExp(user);
	}

	public static byte[] getExpImg(int expId) {

		return expDao.getExpImg(expId);
	}

	public static List<Expense> getAllExpMgr() {
		
		return expDao.getAllExpMgr();
	}

	public static void approve(String expId, int mgrId) {
		
		expDao.approve(expId, mgrId);
		
	}

	public static void deny(String expId, int id) {
		expDao.deny( expId, id);
	}

	
}
