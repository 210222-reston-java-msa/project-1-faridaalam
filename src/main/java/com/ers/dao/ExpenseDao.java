package com.ers.dao;

import java.util.List;

import com.ers.models.Expense;
import com.ers.models.ExpenseTemplate;
import com.ers.models.User;

public interface ExpenseDao {
	
	public int insert(int id, Expense expense);

	public List<Expense> getAllExp(User user);

	public byte[] getExpImg(int expId);

	public List<Expense> getAllExpMgr();

	public void approve(String expId, int mgrId);

	public void deny(String expId, int id);
	
}
