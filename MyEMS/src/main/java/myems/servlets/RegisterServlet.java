package myems.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myems.dao.EmployeeDao;
import myems.entities.Employee;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;character=UTF-8");
		try(PrintWriter out = response.getWriter()){
			try {
				HttpSession httpSession =request.getSession();
				String empName = request.getParameter("empName");
				String empEmail = request.getParameter("empEmail");
				String empPassword = request.getParameter("empPassword");
				Date empJoinDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("empJoinDate"));
				int empSalary = Integer.parseInt(request.getParameter("empSalary"));
				
				if(empName.isEmpty()) {
					
					httpSession.setAttribute("errorPage", "Please input your name again");
					response.sendRedirect("register.jsp");
				}
				
				else if(empEmail.isEmpty()) {
					httpSession.setAttribute("errorPage", "Please input your email again");
					response.sendRedirect("register.jsp");
				}
				
				else if(empPassword.isBlank()) {
					httpSession.setAttribute("errorPage", "Please input your password again");
					response.sendRedirect("register.jsp");
				}
	
				else {
				
				//Creating Employee object to store data
				
				Employee employee = new Employee(empName, empEmail, empPassword, empJoinDate, empSalary, "normal");
				EmployeeDao employeeDao = new EmployeeDao();
				employeeDao.insertEmployee(employee);
				httpSession.setAttribute("message", "Registration successfull!");
				System.out.println("Employee Added");
				Employee currentEmployee=(Employee)httpSession.getAttribute("current-employee");
				if(currentEmployee==null){
					response.sendRedirect("login.jsp");
					}
					else{
						response.sendRedirect("admin.jsp");
						}
					}
				return;
			}catch(Exception e) {
				e.printStackTrace();
				HttpSession httpSession =request.getSession();
				httpSession.setAttribute("errorPage", "Please try again! ");
				response.sendRedirect("register.jsp");
				return;
		}
	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
