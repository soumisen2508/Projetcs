package myems.entities;

import java.util.Date;

public class Employee {
	
	private int empId;
	private String empName;
	private String empPassword;
	private String empEmail;
	private Date empJoinDate;
	private int empSalary;
	private String empType;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public Date getEmpJoinDate() {
		return empJoinDate;
	}
	public void setEmpJoinDate(Date empJoinDate) {
		this.empJoinDate = empJoinDate;
	}
	public int getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}
	
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public Employee() {
		super();
	}
	
	
	public Employee(int empId, String empName, String empPassword, String empEmail, Date empJoinDate, int empSalary,
			String empType) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empPassword = empPassword;
		this.empEmail = empEmail;
		this.empJoinDate = empJoinDate;
		this.empSalary = empSalary;
		this.empType = empType;
	}
	public Employee(int empId, String empName, String empEmail, Date empJoinDate, int empSalary, String empType) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empJoinDate = empJoinDate;
		this.empSalary = empSalary;
		this.empType = empType;
	}
	public Employee(String empName, String empEmail,String empPassword, Date empJoinDate, int empSalary, String empType) {
		super();
		this.empName = empName;
		this.empEmail = empEmail;
		this.empJoinDate = empJoinDate;
		this.empSalary = empSalary;
		this.empType = empType;
		this.empPassword = empPassword;
	}
	public Employee(String empName, String empEmail, Date empJoinDate, int empSalary, String empType) {
		super();
		this.empName = empName;
		this.empEmail = empEmail;
		this.empJoinDate = empJoinDate;
		this.empSalary = empSalary;
		this.empType = empType;
	}
	
	public Employee(int empId, String empName, String empEmail, Date empJoinDate, int empSalary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empJoinDate = empJoinDate;
		this.empSalary = empSalary;
	}
	
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empPassword=" + empPassword + ", empEmail="
				+ empEmail + ", empJoinDate=" + empJoinDate + ", empSalary=" + empSalary + ", empType=" + empType + "]";
	}
	
	
}
