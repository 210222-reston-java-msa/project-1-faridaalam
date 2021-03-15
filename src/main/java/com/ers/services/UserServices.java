package com.ers.services;

import com.ers.dao.ErsDao;
import com.ers.dao.ErsDaoImpl;
import com.ers.models.User;

public class UserServices {

	public static ErsDao eDao = new ErsDaoImpl();

	public static User login(String email, String password) {
		
		return eDao.login(email, password);
	}

	public static User signUp(User u) {
	
		return eDao.signUp(u);
	}

	public static boolean userExist(User u) {
	
		return eDao.userExist(u);
	}

	public static User findUserByEmail(String email) {
	
		return eDao.findUserByEmail(email);
	}

	public static boolean update(User newUser) {
		// TODO Auto-generated method stub
		return eDao.update(newUser);
	}

	public static User findUserById(int mgrId) {
		// TODO Auto-generated method stub
		return eDao.findUserById(mgrId);
	}
	
	
	
	
}
