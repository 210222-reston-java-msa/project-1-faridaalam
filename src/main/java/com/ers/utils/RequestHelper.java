package com.ers.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ers.models.ExpAppDen;
import com.ers.models.Expense;
import com.ers.models.ExpenseTemplate;
import com.ers.models.LoginTemplate;
import com.ers.models.UpdateUserTemplate;
import com.ers.models.User;
import com.ers.services.ExpenseService;
import com.ers.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RequestHelper {
	
	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();

	public static void processLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		// We want to turn whatever we recieve as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		
		// I'm going to build a model called LoginTemplate which holds a username and passwrod
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		
		String email = loginAttempt.getEmail();
		String password = loginAttempt.getPassword();	
		
		log.info("User attempted to login with email: " + email);
		User user = UserServices.login(email, password);
	
		if (user != null) {
	
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("email", email);
			
			// Attaching the print writer to our response
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			// this is converting our Java Object (with property firstName!) 
			// to JSON format....that means we can grab the firstName property
			// after we parse it. (We parse it in JavaScript)

			pw.println(om.writeValueAsString(user));
			
			
			log.info(email + " has successfully logged in");	
		}else {
				res.setStatus(204); // this means that we still have a connection, but no user is found
		}
		
	
		
	}

	public static void processLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.getSession().invalidate();
		response.sendRedirect("index.html");
	}



	public static void processError(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	public static void processSignup(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
	
				BufferedReader reader = req.getReader();
				StringBuilder s = new StringBuilder();
				
				String line = reader.readLine();
				while (line != null) {
					s.append(line);
					line = reader.readLine();
				}
				
				String body = s.toString();
				log.info(body);
				
				
				User u = om.readValue(body, User.class); // from JSON --> Java Object
						
				log.info("User attempted to sign up with email: " + u.getEmail());
				
				PrintWriter pw = res.getWriter();
				res.setContentType("application/json");
				String data;
				if (UserServices.userExist(u)) { // returns true if user exist in database.
					res.setStatus(204);
					data = new JSONObject()
											.put("msg", "")
											.toString();
					pw.write(data);
					log.info(u.getEmail() + " Already exists." +data);
					return;
				} else {
					
					u.setRole(1);
					User user = UserServices.signUp(u);
					
					HttpSession session = req.getSession();
					session.setAttribute("email", user.getEmail());
					
					res.setStatus(200);
				
					pw.println(om.writeValueAsString(user));
					
					log.info(user.getEmail() + " has successfully Signed up");	
				}
				
	}

	public static void processAddExpense(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
		String email = (String) req.getSession().getAttribute("email");
		User user = UserServices.findUserByEmail(email);
	
		if (user == null) {
			res.setStatus(401);
			return;
		} else if (user.getRole() == 2) {
			res.setStatus(403);
			return;
		}

		
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
	
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
			
		
		// I'm going to build a model called LoginTemplate which holds a username and passwrod
		ExpenseTemplate expenseTemp = om.readValue(body, ExpenseTemplate.class); // from JSON --> Java Object
		Expense exp = new Expense();
		
		if (expenseTemp.getImage() != null) {
			System.out.println(expenseTemp.getImage().substring(0,40));
		exp.setImage(Base64.getDecoder().decode(expenseTemp.getImage().substring(expenseTemp.getImage().indexOf(",")+1))); // Storing image as binary
		exp.setImgAdded(true);
		} else {
			exp.setImgAdded(false);
		}
		exp.setAmount(expenseTemp.getAmount());
		exp.setNote(expenseTemp.getNote());
		
		if (expenseTemp.getType().equalsIgnoreCase("Lodging")) 
			exp.setType(1);
		else if (expenseTemp.getType().equalsIgnoreCase("Travel")) 
			exp.setType(2);
		else if (expenseTemp.getType().equalsIgnoreCase("Food")) 
			exp.setType(3);
		else  
			exp.setType(4);
		
		exp.setStatus(1);
		exp.setTime(LocalDateTime.now());
		
		
		log.info("expense is adding with amount: " + expenseTemp.getAmount());
		
		int expId = ExpenseService.insert(user.getId(), exp);
		
		exp.setId(expId);
		
		
			
			// Attaching the print writer to our response
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			// this is converting our Java Object (with property firstName!) 
			// to JSON format....that means we can grab the firstName property
			// after we parse it. (We parse it in JavaScript)
			res.setStatus(200);
			
			
			log.info(" Expense successfully added" + exp.getId());	
			
	
	
	
	}

	
	public static void processRetrieveExpense(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String email = (String) req.getSession().getAttribute("email");
		
		User user = UserServices.findUserByEmail(email);
		if (user == null) {
			res.setStatus(401);
			return;
		} else if (user.getRole() == 2) {
			res.setStatus(403);
			return;
		}
		
		List<Expense> expList = ExpenseService.getAllExp(user);
		
		String tableTemp = fReader("/home/farid/revature/week2/EmpReimSys/src/main/webapp/template/EmpExpTbl.htm");
		String trTemp = fReader("/home/farid/revature/week2/EmpReimSys/src/main/webapp/template/EmpExpTr.htm");
		String modalTemp = fReader("/home/farid/revature/week2/EmpReimSys/src/main/webapp/template/EmpTblMdl.html");
		
		String tbl= "";
		int tSize = expList.size();
		if ( tSize> 0) {
			tbl = new String(tableTemp);
		}
		int rowCounter = 0;
		String row = "";
		String rows = ""; 
		String tempType, tempStatus, modal;
		for (Expense exp : expList) {
			rowCounter++;
			row = new String (trTemp);
			modal = new String(modalTemp);
			
			row = row.replace("rowID", "row" + ((rowCounter %2) +1) );
			row= row.replace("idX", String.valueOf(exp.getId()));
			row= row.replace("amount", String.valueOf(exp.getAmount()));
			tempType = convertType(exp.getType());
			row = row.replace("type", tempType);
			row = row.replace("date", String.valueOf(exp.getTime()));
			tempStatus = convertStatus(exp.getStatus());
			row = row.replace("status", tempStatus);
			
			if (exp.getStatus()==1) {
				row = row.replace("rowClass", "rowPending");
			} else if (exp.getStatus()==2) {
				row = row.replace("rowClass", "rowApproved");
			} else if (exp.getStatus()==3) {
				row = row.replace("rowClass", "rowDenied");
			}
			
			
			modal = modal.replaceAll("mID", String.valueOf(exp.getId()));
			modal = modal.replaceAll("modalID", "modal"+ exp.getId());
			modal = modal.replaceAll("expID", "Detail of expense #" + exp.getId());
			
			if (exp.isImgAdded()) {
				modal = modal.replace("imgMod", "<img class=\"img-fluid\" src= \"expimage?id="+ exp.getId() + "\" />");
				
			}else {
				modal = modal.replace("imgMod", "<img class=\"img-fluid\" src= \"images/default-expense.jpg\" />" );
			}

			
			modal = modal.replace("commentEmp", exp.getNote());
			
			
			row= row.replace("modal", modal);
			
			rows += row; 
			row = "";
			}
		
			tbl =tbl.replace("trAdding", rows);
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			res.setStatus(200);
			pw.println(tbl);
			
		
	}

	public static void processExpImages(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
		String email = (String) req.getSession().getAttribute("email");
		
		User user = UserServices.findUserByEmail(email);
		if (user == null) {
			res.setStatus(401);
			return;
		} 
		
		String expIds = req.getParameter("id");
		
		int expId = Integer.valueOf(expIds);
		
		byte[] image = ExpenseService.getExpImg(expId);
		

            OutputStream os = res.getOutputStream();


                res.setContentType("image/jpeg");

              
                	if (image!=null) {
                    os.write(image);
                	}else
                		System.out.println(image);
                	System.out.println(expId);
     
        
        
		
		log.info(" Image sent " + expId);	
		
	}
	
public static void processEditprofile(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
	String email = (String) req.getSession().getAttribute("email");
	
	User user = UserServices.findUserByEmail(email);
	if (user == null) {
		res.setStatus(401);
		return;
	}
	
	
	BufferedReader reader = req.getReader();
	StringBuilder s = new StringBuilder();
	
	String line = reader.readLine();
	while (line != null) {
		s.append(line);
		line = reader.readLine();
	}
	
	String body = s.toString();
	
	
	UpdateUserTemplate u = om.readValue(body, UpdateUserTemplate.class); // from JSON --> Java Object
			
	log.info("User attempted to edit profile with email: " + u.getEmail());
	System.out.println(u.getNewPword1() ==null);
	if (user.getPassword().equals(u.getPassword())&& (!(u.getNewPword1().isBlank())) && (u.getNewPword1().equals(u.getNewPword2()))) {
		User newUser = new User();
		
		newUser.setFirstName(u.getFirstName());
		newUser.setLastName(u.getLastName());
		newUser.setId(user.getId());
		newUser.setPassword(u.getNewPword1());
		
		if(UserServices.update(newUser)) {
			user = UserServices.findUserByEmail(email);
		}
		
		log.info("User updated" + email);
		
	}else if (((u.getNewPword1().isEmpty()) &&(u.getNewPword2().isEmpty())) && (user.getPassword().equals(u.getPassword()))) {
User newUser = new User();
		
		newUser.setFirstName(u.getFirstName());
		newUser.setLastName(u.getLastName());
		newUser.setId(user.getId());
		newUser.setPassword(user.getPassword());
		
		if(UserServices.update(newUser)) {
			user = UserServices.findUserByEmail(email);
		}
		
		log.info("User updated" + email);
		
		
	}else {
		res.setStatus(204);
		return;
	}
	
	PrintWriter pw = res.getWriter();
	res.setContentType("application/json");
	String data;
	
		
		HttpSession session = req.getSession();
		session.setAttribute("email", user.getEmail());
		
		res.setStatus(200);
	
		pw.println(om.writeValueAsString(user));
		
	

	
	
	}
	
	
	
	private static String convertStatus(int status) {
		
		if (status == 1) return "Pending";
		else if (status == 2) return "Approved";
		else return "Denied";

	}

	public static String convertType(int type) {
			
		if (type == 1) return "Lodging";
		else if (type == 2) return "Travel";
		else if (type == 3) return "Food";
		else return "Other";
		
	}

	public static String fReader(String fileAddress) {
		String data = "";
		try { 
			FileReader file = new FileReader(fileAddress);
			Scanner sc = new Scanner(file);
			
			while (sc.hasNextLine()) {
				data += sc.nextLine();
			}
			sc.close();
			log.info( fileAddress + " was read successfully");
		}
		catch(FileNotFoundException e) {
			  log.warn("Error reading  files.");
		      e.printStackTrace();
		}
		return data;
	}

	public static void processReadAllExp(HttpServletRequest req, HttpServletResponse res) throws IOException {

	String email = (String) req.getSession().getAttribute("email");
		
		User user = UserServices.findUserByEmail(email);
		if (user == null) {
			res.setStatus(401);
			return;
		} else if (user.getRole() == 1) {
			res.setStatus(403);
			return;
		}
		
		List<Expense> expList = ExpenseService.getAllExpMgr();
		
		String tableTemp = fReader("/home/farid/revature/week2/EmpReimSys/src/main/webapp/template/MgrExpTbl.htm");
		String trTemp = fReader("/home/farid/revature/week2/EmpReimSys/src/main/webapp/template/MgrExpTr.htm");
		String modalTemp = fReader("/home/farid/revature/week2/EmpReimSys/src/main/webapp/template/MgrTblMdl.html");
		
		String tbl= "";
		int tSize = expList.size();
		if ( tSize> 0) {
			tbl = new String(tableTemp);
		}
		int rowCounter = 0;
		String row = "";
		String rows = ""; 
		String tempType, tempStatus, modal;
		User manager , ux; 
		for (Expense exp : expList) {
			rowCounter++;
			row = new String (trTemp);
			modal = new String(modalTemp);
			
			row = row.replace("rowID", "row" + ((rowCounter %2) +1) );
			row= row.replace("idX", String.valueOf(exp.getId()));
			
			ux = UserServices.findUserById(exp.getUserId());
			row = row.replace("uName", ux.getFirstName() + " " + ux.getLastName());
			
			row= row.replace("amount", String.valueOf(exp.getAmount()));
			tempType = convertType(exp.getType());
			row = row.replace("type", tempType);
			row = row.replace("date", String.valueOf(exp.getTime()));
			tempStatus = convertStatus(exp.getStatus());
			row = row.replace("status", tempStatus);
			row = row.replaceAll("exID", String.valueOf( exp.getId()));
			
			if (exp.getStatus()==1) {
				row = row.replace("rowClass", "rowPending");
			} else if (exp.getStatus()==2) {
				row = row.replace("rowClass", "rowApproved");
			} else if (exp.getStatus()==3) {
				row = row.replace("rowClass", "rowDenied");
			}
			
			
			modal = modal.replaceAll("mID", String.valueOf(exp.getId()));
			modal = modal.replaceAll("modalID", "modal"+ exp.getId());
			modal = modal.replaceAll("expID", "Detail of expense #" + exp.getId());
			
			if (exp.isImgAdded()) {
				modal = modal.replace("imgMod", "<img class=\"img-fluid\" src= \"expimage?id="+ exp.getId() + "\" />");
				
			}else {
				modal = modal.replace("imgMod", "<img class=\"img-fluid\" src= \"images/default-expense.jpg\" />" );
			}

			
			modal = modal.replace("commentEmp", exp.getNote());
			System.out.println(exp.getMgrId());
			if (exp.getMgrId() == 0) {
				modal = modal.replace("mgrName", "Manager Not resolved this expense yet.");
			}else {
				manager = UserServices.findUserById(exp.getMgrId());
				modal = modal.replace("mgrName", manager.getFirstName() + " " + manager.getLastName());
			}
			
			row= row.replace("modal", modal);
			
			rows += row; 
			row = "";
			}
		
			tbl =tbl.replace("trAdding", rows);
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			res.setStatus(200);
			pw.println(tbl);
			
		
		
	}

	public static void processApprove(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String email = (String) req.getSession().getAttribute("email");
		
		User user = UserServices.findUserByEmail(email);
		if (user == null) {
			res.setStatus(401);
			return;
		} else if (user.getRole() == 1) {
			res.setStatus(403);
			return;
		}
		
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		
		
		
		// I'm going to build a model called LoginTemplate which holds a username and passwrod
		ExpAppDen app = om.readValue(body, ExpAppDen.class); // from JSON --> Java Object

		
			
		
		log.info("Approving " + app.getExpId());
		
		ExpenseService.approve(app.getExpId(), user.getId());
		
		res.setStatus(200);
	}

	public static void processDeny(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String email = (String) req.getSession().getAttribute("email");
		
		User user = UserServices.findUserByEmail(email);
		if (user == null) {
			res.setStatus(401);
			return;
		} else if (user.getRole() == 1) {
			res.setStatus(403);
			return;
		}
		
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		
		
		
		// I'm going to build a model called LoginTemplate which holds a username and passwrod
		ExpAppDen app = om.readValue(body, ExpAppDen.class); // from JSON --> Java Object

		
			
		
		log.info("Denying " + app.getExpId());
		
		ExpenseService.deny(app.getExpId(), user.getId());
		
		res.setStatus(200);		
	}

	


}
