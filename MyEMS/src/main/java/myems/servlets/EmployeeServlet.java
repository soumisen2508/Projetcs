package myems.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myems.dao.EmployeeDao;
import myems.entities.Employee;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
       
    public EmployeeServlet() {
        super();    
    }
    
	public void init() throws ServletException {
		employeeDao = new EmployeeDao();
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println(action);
		switch(action) {
		case "new":
			showNewForm(request, response);
			break;
	    case "insert":
	    	try {
				insertEmployee(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
	    case "delete":
	    	deleteEmployee(request, response);
			break;
	    case "edit":
	    	showEditForm(request, response);
			break;
	    case "update":
	    	try {
				updateEmployee(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
	    	System.out.println("Employee updated");
			break;
	    case "payslip":
              try {
				payslipGenerate(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
              break;
	   case "search" :
			try {
				searchEmployee(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		   break;
			default:
			try {
				listEmployee(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				break;
		}

		
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertEmployee(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException, ParseException {
		String empName = request.getParameter("empName");
		String empPassword = request.getParameter("empPassword");
		String empEmail = request.getParameter("empEmail");
		Date empJoinDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("empJoinDate"));
		int empSalary = Integer.parseInt(request.getParameter("empSalary"));
		Employee newEmp = new Employee(empName, empEmail,empPassword,empJoinDate, empSalary,"normal");
		
		employeeDao.insertEmployee(newEmp);
		response.sendRedirect("list");
	}
	
	private void payslipGenerate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		try {
			List<Employee> listEmployee = employeeDao.selectAllEmployeesByAnnualSalary();
			request.setAttribute("salaryListEmployee", listEmployee);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_list.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void searchEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		try {
			String[] searchStringArray = request.getParameter("searchString").split(",", 3);
			if(searchStringArray[0].isBlank()) {
				if(searchStringArray[1].isBlank()) {
					if(searchStringArray[2].isBlank()) {
						HttpSession httpSession = request.getSession();
						httpSession.setAttribute("errorPage", "Input string is empty.Please try again.");
						response.sendRedirect("search.jsp");
					}
					
					else {
						String searchString3 = searchStringArray[2].substring(0, 2);
						List<Employee> listEmployee = employeeDao.searchEmployeeByempSalary(searchString3);
						request.setAttribute("searchListEmployee", listEmployee);
						System.out.println("searchstring is " +searchString3);
					}
				}
				
				else {
				if(searchStringArray[2].isBlank()) {
					String searchString2 = searchStringArray[1].substring(0, 3);
					List<Employee> listEmployee = employeeDao.searchEmployeeByempJoinDate(searchString2);
					request.setAttribute("searchListEmployee", listEmployee);
					System.out.println("searchstring is " +searchString2);
				}
				
				else {
					String searchString2 = searchStringArray[1].substring(0, 2);
					String searchString3 = searchStringArray[2].substring(0, 3);
					List<Employee> listEmployee = employeeDao.searchEmployeeByempJoinDateandempSalary(searchString2,searchString3 );
					request.setAttribute("searchListEmployee", listEmployee);
					System.out.println("searchstring is " +searchString2+searchString3);
					
				}
				}
			}
			
			else {
				if(searchStringArray[1].isBlank()) {
					if(searchStringArray[2].isBlank()) {
						String searchString1 = searchStringArray[0].substring(0, 2);
						List<Employee> listEmployee = employeeDao.searchEmployeeByempName(searchString1);
						request.setAttribute("searchListEmployee", listEmployee);
						System.out.println("searchstring is " +searchString1);
					}
					else {
						String searchString1 = searchStringArray[0].substring(0, 2);
						String searchString3 = searchStringArray[2].substring(0, 2);
						List<Employee> listEmployee = employeeDao.searchEmployeeByempNameandempSalary(searchString1,searchString3);
						request.setAttribute("searchListEmployee", listEmployee);
						System.out.println("searchstring is " +searchString1+searchString3);
						
					}
				}
				
				else {
					if(searchStringArray[2].isBlank()) {
						String searchString1 = searchStringArray[0].substring(0, 2);
						String searchString2 = searchStringArray[1].substring(0, 2);
						List<Employee> listEmployee = employeeDao.searchEmployeeByempNameandempJoinDate(searchString1,searchString2);
						request.setAttribute("searchListEmployee", listEmployee);
						System.out.println("searchstring is " +searchString1+searchString2);
					}
					
					else {
						String searchString1 = searchStringArray[0].substring(0, 2);
						String searchString2 = searchStringArray[1].substring(0, 3);
						String searchString3 = searchStringArray[2].substring(0, 2);
						List<Employee> listEmployee = employeeDao.searchEmployee(searchString1,searchString2,searchString3);
						request.setAttribute("searchListEmployee", listEmployee);
						System.out.println("searchstring is " +searchString1+searchString2+searchString3);
					}
				}
				
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("user_list.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			employeeDao.deleteEmployee(empId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("admin.jsp");
		System.out.println("Employee deleted");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int empId = Integer.parseInt(request.getParameter("empId"));
		Employee existingEmployee;
		try {
			existingEmployee = employeeDao.selectEmployee(empId);
			request.setAttribute("employee", existingEmployee);
			System.out.println("your employee id is: "+existingEmployee.getEmpId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException, ParseException{
		int empId = Integer.parseInt(request.getParameter("empId"));
		String empName = request.getParameter("empName");
		String empEmail = request.getParameter("empEmail");
		String empPassword = request.getParameter("empPassword");
		Date empJoinDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("empJoinDate"));
		int empSalary = Integer.parseInt(request.getParameter("empSalary"));
		Employee emp = new Employee(empId, empName, empPassword, empEmail, empJoinDate, empSalary, "normal");
		employeeDao.updateEmployee(emp);
		response.sendRedirect("admin.jsp");
	}
	
	private void listEmployee(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		try {
			List<Employee> listEmployee = employeeDao.selectAllEmployees();
			request.setAttribute("listEmployee", listEmployee);
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

