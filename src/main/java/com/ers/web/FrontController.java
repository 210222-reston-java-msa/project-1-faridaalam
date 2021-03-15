package com.ers.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ers.utils.RequestHelper;

/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
    public FrontController() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	final String URI = request.getRequestURI().replace("/EmpReimSys/", "");
		
		switch(URI) {
		case "login":
			System.out.println("hit login");
			RequestHelper.processLogin(request, response);
			break;
		case "logout":
			RequestHelper.processLogout(request, response);
			break;
		case "signup":
			RequestHelper.processSignup(request, response);
			break;
		case "addExpense":
			RequestHelper.processAddExpense(request,response);
			break;
		case "readExpEmp":
			RequestHelper.processRetrieveExpense(request,response);
			break;
		case "expimage":
			RequestHelper.processExpImages(request,response);
			break;
		case "editprofile":
			RequestHelper.processEditprofile(request,response);
			break;
		case "readAllExp":
			RequestHelper.processReadAllExp(request,response);
			break;
		case "approve":
			RequestHelper.processApprove(request,response);
			break;
		case "deny":
			RequestHelper.processDeny(request,response);
			break;
		case "error":
			RequestHelper.processError(request,response);
			break;
		} 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}