package com.ers.dao;

import com.ers.models.User;

public interface ErsDao {
	

	public boolean update(User user);
	
	public User login(String email, String password);
	public boolean userExist(User u);
	
	public User signUp(User u);
	public User findUserByEmail(String email);

	public User findUserById(int mgrId);
	
	

}
