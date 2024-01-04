package myems.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import myems.entities.Employee;

public class EmployeeDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/emsdb?useSSL=false";
	private String jdbcUsername ="root";
	private String jdbcPassword = "Wittylotus_494";
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	
	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee" + " (empName, empPassword, empEmail, empJoinDate, empSalary, empType) VALUES " + "(?, ?, ?, ?, ?, ?);";
	private static final String SELECT_EMPLOYEE_BY_ID = "select empId,empName,empEmail,empJoinDate,empSalary, empType from employee where empId=?";
	private static final String SELECT_EMPLOYEE_BY_EMAIL_AND_PASSWORD = "select empId,empName,empPassword,empEmail,empType from employee where empPassword=? and empEmail=?";
	private static final String SELECT_All_EMPLOYEES = "select * from employee";
	private static final String DELETE_EMPLOYEE_SQL = "delete from employee where empId=?;";
	private static final String UPDATE_EMPLOYEE_SQL ="update employee set empName = ?, empPassword = ?, empEmail = ?, empJoinDate = ?, empSalary = ? where empId = ?";
	private static final String SELECT_EMPLOYEE_ANNUAL_SALARY_SQL = "select empId,empName, empSalary * 12 AS Annual_Salary from employee order by Annual_Salary ASC;";
	private static final String SEARCH_EMPLOYEE_BY_STRING ="select empId,empName,empJoinDate,empSalary from employee where empName LIKE ? AND empJoinDate LIKE ? AND empSalary LIKE ? ;";
	private static final String SEARCH_EMPLOYEE_BY_STRING3 ="select empId,empName,empJoinDate,empSalary from employee where empSalary LIKE ? ;";
	private static final String SEARCH_EMPLOYEE_BY_STRING2 ="select empId,empName,empJoinDate,empSalary from employee where empJoinDate LIKE ? ;";
	private static final String SEARCH_EMPLOYEE_BY_STRING1 ="select empId,empName,empJoinDate,empSalary from employee where empName LIKE ? ;";
	
	private static final String SEARCH_EMPLOYEE_BY_STRING2_AND_STRING3 ="select empId,empName,empJoinDate,empSalary from employee where empJoinDate LIKE ? AND empSalary LIKE ? ;";
	private static final String SEARCH_EMPLOYEE_BY_STRING1_AND_STRING3 ="select empId,empName,empJoinDate,empSalary from employee where empName LIKE ? AND empSalary LIKE ? ;";
	private static final String SEARCH_EMPLOYEE_BY_STRING1_AND_STRING2 ="select empId,empName,empJoinDate,empSalary from employee where empName LIKE ? AND empJoinDate LIKE ? ;";

	public EmployeeDao() {
		
	}
	
	protected Connection getConnection() throws SQLException{
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword );
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	//insert employee record
	
	public void insertEmployee(Employee employee) throws SQLException{
		System.out.println(INSERT_EMPLOYEE_SQL);
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT_EMPLOYEE_SQL)){
			ps.setString(1 , employee.getEmpName());
			ps.setString(2 , employee.getEmpPassword());
			ps.setString(3, employee.getEmpEmail());
			ps.setDate(4, new java.sql.Date(employee.getEmpJoinDate().getTime()));
			ps.setInt(5, employee.getEmpSalary());
			ps.setString(6, employee.getEmpType());
			System.out.println(ps);
			ps.executeUpdate();
			ps.close();
			connection.close();
			
		}catch(SQLException e) {
			printSQLException(e);
		}
	}

	
	//select employee by id
	
	public Employee selectEmployee(int id) {
		Employee employee = new Employee();
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID);){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int empId = rs.getInt("empId");
				String empName = rs.getString("empName");
				String empEmail = rs.getString("empEmail");
				Date empDate = rs.getDate("empJoinDate");
				int empSalary = rs.getInt("empSalary");
				String empType = rs.getString("empType");
				employee = new Employee(empId, empName, empEmail, empDate, empSalary, empType);
				System.out.println("salary(from dao):"+ employee.getEmpSalary());
			}
			System.out.println(ps);
			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return employee;
		
	}
	
	//select employee by email and password
	
	public Employee getEmployeeByEmailAndPassword(String empPassword,String empEmail) {
		Employee employee = null;
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_BY_EMAIL_AND_PASSWORD);){
			ps.setString(1, empPassword);
			ps.setString(2, empEmail);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			while(rs.next()) {
				int empId = rs.getInt("empId");
				String empName = rs.getString("empName");
				String password = rs.getString("empPassword");
				String email = rs.getString("empEmail");
				String type = rs.getString("empType");
				System.out.println(empId);
				employee = new Employee(empId, empName, password, email, null, 0, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
		
	}
	
	//select all employees
	
	public List<Employee> selectAllEmployees(){
		List <Employee> employees = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_All_EMPLOYEES);){
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int empId = rs.getInt("empId");
				String empName = rs.getString("empName");
				String empEmail = rs.getString("empEmail");
				Date empJoinDate = rs.getDate("empJoinDate");
				int empSalary = rs.getInt("empSalary");
				employees.add(new Employee(empId, empName, empEmail, empJoinDate, empSalary));	
			}
		
			} catch (SQLException e) {
				printSQLException(e);
			}
		
		return employees;
	}
	
	//search a given employee
	
	public List<Employee> searchEmployee(String searchString1, String searchString2, String searchString3){
		List <Employee> employees = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SEARCH_EMPLOYEE_BY_STRING.toString());){
			String inputString1 = "%" + searchString1 + "%";
			String inputString2 = "%" + searchString2 + "%";
			String inputString3 = "%" + searchString3 + "%";
			ps.setString(1, inputString1);
			ps.setString(2, inputString2);
			ps.setString(3, inputString3);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int empId = rs.getInt("empId");
				String empName = rs.getString("empName");
				Date empJoinDate = rs.getDate("empJoinDate");
				int empSalary = rs.getInt("empSalary");
				System.out.println(empName);
				employees.add(new Employee(empId, empName, "", "", empJoinDate, empSalary, ""));
			}
			System.out.println(ps);
			} catch (SQLException e) {
				printSQLException(e);
			}
		return employees;
	}
	
	//search a given employee by empSalary
	
	public List<Employee> searchEmployeeByempSalary(String searchString3){
		List <Employee> employees = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SEARCH_EMPLOYEE_BY_STRING3.toString());){
			String inputString3 = "%" + searchString3 + "%";
			ps.setString(1, inputString3);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int empId = rs.getInt("empId");
				String empName = rs.getString("empName");
				Date empJoinDate = rs.getDate("empJoinDate");
				int empSalary = rs.getInt("empSalary");
				System.out.println(empName);
				employees.add(new Employee(empId, empName, "", "", empJoinDate, empSalary, ""));
			}
			System.out.println(ps);
			} catch (SQLException e) {
				printSQLException(e);
			}
		return employees;
	}
	
	//search a given employee by empName
	
		public List<Employee> searchEmployeeByempName(String searchString1){
			List <Employee> employees = new ArrayList<>();
			try(Connection connection = getConnection();
					PreparedStatement ps = connection.prepareStatement(SEARCH_EMPLOYEE_BY_STRING1 .toString());){
				String inputString1 = "%" + searchString1 + "%";
				ps.setString(1, inputString1);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int empId = rs.getInt("empId");
					String empName = rs.getString("empName");
					Date empJoinDate = rs.getDate("empJoinDate");
					int empSalary = rs.getInt("empSalary");
					System.out.println(empName);
					employees.add(new Employee(empId, empName, "", "", empJoinDate, empSalary, ""));
				}
				System.out.println(ps);
				} catch (SQLException e) {
					printSQLException(e);
				}
			return employees;
		}
	
	//search a given employee by empJoinDate
	
		public List<Employee> searchEmployeeByempJoinDate(String searchString2){
			List <Employee> employees = new ArrayList<>();
			try(Connection connection = getConnection();
					PreparedStatement ps = connection.prepareStatement(SEARCH_EMPLOYEE_BY_STRING2.toString());){
				String inputString2 = "%" + searchString2 + "%";
				ps.setString(1, inputString2);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int empId = rs.getInt("empId");
					String empName = rs.getString("empName");
					Date empJoinDate = rs.getDate("empJoinDate");
					int empSalary = rs.getInt("empSalary");
					System.out.println(empName);
					employees.add(new Employee(empId, empName, "", "", empJoinDate, empSalary, ""));
				}
				System.out.println(ps);
				} catch (SQLException e) {
					printSQLException(e);
				}
			return employees;
		}
		
		//search a given employee by empJoinDate and empSalary
		
		public List<Employee> searchEmployeeByempJoinDateandempSalary(String searchString2, String searchString3){
			List <Employee> employees = new ArrayList<>();
			try(Connection connection = getConnection();
					PreparedStatement ps = connection.prepareStatement(SEARCH_EMPLOYEE_BY_STRING2_AND_STRING3.toString());){
				String inputString2 = "%" + searchString2 + "%";
				String inputString3 = "%" + searchString3 + "%";
				ps.setString(1, inputString2);
				ps.setString(2, inputString3);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					int empId = rs.getInt("empId");
					String empName = rs.getString("empName");
					Date empJoinDate = rs.getDate("empJoinDate");
					int empSalary = rs.getInt("empSalary");
					System.out.println(empName);
					employees.add(new Employee(empId, empName, "", "", empJoinDate, empSalary, ""));
				}
				System.out.println(ps);
				} catch (SQLException e) {
					printSQLException(e);
				}
			return employees;
		}
		
		//search a given employee by empName and empSalary
		
				public List<Employee> searchEmployeeByempNameandempSalary(String searchString1, String searchString3){
					List <Employee> employees = new ArrayList<>();
					try(Connection connection = getConnection();
							PreparedStatement ps = connection.prepareStatement(SEARCH_EMPLOYEE_BY_STRING1_AND_STRING3.toString());){
						String inputString2 = "%" + searchString1 + "%";
						String inputString3 = "%" + searchString3 + "%";
						ps.setString(1, inputString2);
						ps.setString(2, inputString3);
						ResultSet rs = ps.executeQuery();
						while(rs.next()) {
							int empId = rs.getInt("empId");
							String empName = rs.getString("empName");
							Date empJoinDate = rs.getDate("empJoinDate");
							int empSalary = rs.getInt("empSalary");
							System.out.println(empName);
							employees.add(new Employee(empId, empName, "", "", empJoinDate, empSalary, ""));
						}
						System.out.println(ps);
						} catch (SQLException e) {
							printSQLException(e);
						}
					return employees;
				}
				
				//search a given employee by empName and empJoinDate
				
				public List<Employee> searchEmployeeByempNameandempJoinDate(String searchString1, String searchString2){
					List <Employee> employees = new ArrayList<>();
					try(Connection connection = getConnection();
							PreparedStatement ps = connection.prepareStatement(SEARCH_EMPLOYEE_BY_STRING1_AND_STRING2 .toString());){
						String inputString1 = "%" + searchString1 + "%";
						String inputString2 = "%" + searchString2 + "%";
						ps.setString(1, inputString1);
						ps.setString(2, inputString2);
						ResultSet rs = ps.executeQuery();
						while(rs.next()) {
							int empId = rs.getInt("empId");
							String empName = rs.getString("empName");
							Date empJoinDate = rs.getDate("empJoinDate");
							int empSalary = rs.getInt("empSalary");
							System.out.println(empName);
							employees.add(new Employee(empId, empName, "", "", empJoinDate, empSalary, ""));
						}
						System.out.println(ps);
						} catch (SQLException e) {
							printSQLException(e);
						}
					return employees;
				}
		
		
	//select annual salary of all employees
	
	public List<Employee> selectAllEmployeesByAnnualSalary(){
		List <Employee> employees = new ArrayList<>();
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_ANNUAL_SALARY_SQL);){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int empId = rs.getInt("empId");
				String empName = rs.getString("empName");
				int empAnnualSalary = rs.getInt("Annual_Salary");
				employees.add(new Employee(empId, empName, "", "", null, empAnnualSalary, ""));	
			}
			System.out.println(ps);
			} catch (SQLException e) {
				printSQLException(e);
			}
		
		return employees;
	}
	
	//update employee based on id
	
	public boolean updateEmployee(Employee employee) throws SQLException{
		boolean rowUpdated = false;
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);){
			ps.setString(1, employee.getEmpName());
			ps.setString(2, employee.getEmpPassword());
			ps.setString(3, employee.getEmpEmail());
			ps.setDate(4, new java.sql.Date(employee.getEmpJoinDate().getTime()));
			ps.setInt(5, employee.getEmpSalary());
			ps.setInt(6, employee.getEmpId());
			rowUpdated = ps.executeUpdate() > 0;
			System.out.println("Updated User: " + ps);
			connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}
	
	//delete employee based on id
	
	public boolean deleteEmployee(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE_EMPLOYEE_SQL);){
			ps.setInt(1, id);
			rowDeleted = ps.executeUpdate() > 0;
		}
		return rowDeleted;
		
	}
	
	private void printSQLException(SQLException ex) {
		for(Throwable e: ex) {
			if(e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while(t!=null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
		
	}
}
