package myems.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import myems.dao.EmployeeDao;
import myems.entities.Employee;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
        super(); 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;character=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String empEmail = request.getParameter("empEmail");
			String empPassword = request.getParameter("empPassword");
			
			//authenticating users
			    EmployeeDao employeeDao = new EmployeeDao();
			    Employee employee=employeeDao.getEmployeeByEmailAndPassword(empPassword, empEmail);
			    HttpSession httpSession = request.getSession();
			    if(employee==null) {
			    	httpSession.setAttribute("errorPage", "Incorrect E-mail address or Password. Try Again!.");
			    	response.sendRedirect("login.jsp");
			    	return;
			    } else {
			    	out.println("<h1>Welcome!"+employee.getEmpPassword()+"</h1>");
			    	//login
			    	httpSession.setAttribute("current-employee", employee);
			    	if(employee.getEmpType().equals("admin")) {
			    		//admin user:-admin.jsp
			    		response.sendRedirect("admin.jsp");
			    	}else if(employee.getEmpType().equals("normal")){
				    	response.sendRedirect("profile.jsp");
			    	}
			    	else {
			    		out.println("We have not identified user type");
			    	}
			    	
			    }
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
