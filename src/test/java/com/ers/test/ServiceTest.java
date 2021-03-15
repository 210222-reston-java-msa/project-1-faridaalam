package com.ers.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ers.services.UserServices;


public class ServiceTest {
	// class to be tested is employee service
	private UserServices eserv;
	
	// we have to create dependencies that our service layer needs in order to make contact with DB
	private EmployeeDAOImpl edaoImpl;
	
	@Before
	public void setup() {
		
		eserv = new EmployeeService();
		
		// here we are creating a fake DB connection and tricking service layer.
		edaoImpl = mock(EmployeeDAOImpl.class);
		
		// here we set the EmployeeDAOImpl of the service to the one that we've mocked
		eserv.eDao= edaoImpl;
		
	}
	
	// a happy path is a default scenario in which we allow it input and get expected output without exceptions.
	@Test
	public void happyPathScenario() {
		
		Employee sampleEmployee = new Employee(1, "a", "b", "c" , "d");
		
		// create a fake list representing the list of employee pulled from DB
		List<Employee> list = new ArrayList<Employee>();
		list.add(sampleEmployee);
		// we are essentially programming our fake dao to return this as its fake data
		when(edaoImpl.findAll()).thenReturn(list);
		
		String dummyUsername = sampleEmployee.getUsername();
		//here we are assuring that our service layer's findByUsername()
		// Method will return the employee obj that we supplied our fake persistance layer.
		Employee foundByUsername= eserv.findByUsername(dummyUsername);
		
		assertEquals(sampleEmployee, foundByUsername);
		
	}
	
	@Test
	public void employeeIsNotPresentInDB() {
		
		List<Employee> emptyList = new ArrayList<Employee>();
		
		when(edaoImpl.findAll()).thenReturn(emptyList);
		
		Employee employeeFoundByUsername = eserv.findByUsername("test");
		
		assertEquals(null, employeeFoundByUsername);
	}
	
}
}
