/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.84
 * Generated at: 2023-01-12 05:22:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import myems.entities.Employee;
import myems.entities.Employee;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/components/common_css_js.jsp", Long.valueOf(1673344977658L));
    _jspx_dependants.put("/components/navbar.jsp", Long.valueOf(1673500578149L));
    _jspx_dependants.put("/components/errorPage.jsp", Long.valueOf(1673215332715L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("myems.entities.Employee");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("    \r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>EMS</title>\r\n");
      out.write("<!-- CSS -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/style.css\">\r\n");
      out.write("<!-- JavaScript -->\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.6.3.min.js\" integrity=\"sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write('\r');
      out.write('\n');

Employee employee1=(Employee)session.getAttribute("current-employee");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<nav class=\"navbar navbar-expand-lg navbar-dark custom-bg\">\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("<a class=\"navbar-brand\" href=\"#!\">Employee Management System</a>\r\n");
      out.write("  <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("    <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("  </button>\r\n");
      out.write("\r\n");
      out.write("  <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\r\n");
      out.write("    <ul class=\"navbar-nav ml-auto\">\r\n");
      out.write("    ");

    if(employee1==null){
    	
      out.write("\r\n");
      out.write("    	<li class=\"nav-item active\">\r\n");
      out.write("        <a class=\"nav-link\" href=\"login.jsp\">Login</a>\r\n");
      out.write("      </li>\r\n");
      out.write("    	");

    }
    
    else if(employee1.getEmpType().equals("normal")){
    	
      out.write("\r\n");
      out.write("    	<li class=\"nav-item active\">\r\n");
      out.write("        <a class=\"nav-link\" href=\"LogoutServlet\">Logout</a>\r\n");
      out.write("      </li>\r\n");
      out.write("      ");

    }
    else{
    	
      out.write("\r\n");
      out.write("      <li class=\"nav-item dropdown\">\r\n");
      out.write("        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\r\n");
      out.write("         Menu\r\n");
      out.write("        </a>\r\n");
      out.write("        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"EmployeeServlet?action=new\">Add Employee</a>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"search.jsp\">Search Employee</a>\r\n");
      out.write("          <div class=\"dropdown-divider\"></div>\r\n");
      out.write("          <a class=\"dropdown-item\" href=\"EmployeeServlet?action=payslip\">Generate Payslip</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </li>\r\n");
      out.write("      <li class=\"nav-item active\">\r\n");
      out.write("        <a class=\"nav-link\" href=\"LogoutServlet\">Logout</a>\r\n");
      out.write("      </li>\r\n");
      out.write("    	");

    	
    }
    
      out.write("\r\n");
      out.write(" \r\n");
      out.write("      </ul>\r\n");
      out.write("      </div>\r\n");
      out.write("</div>\r\n");
      out.write("</nav>");
      out.write('\r');
      out.write('\n');
 
String errorPage = (String)session.getAttribute("errorPage");
if(errorPage!=null)
{
	
      out.write("\r\n");
      out.write("  <div class=\"alert alert-danger alert-dismissible fade show\" role=\"alert\">\r\n");
      out.write("  <strong>");
      out.print( errorPage);
      out.write("</strong>\r\n");
      out.write("  <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">\r\n");
      out.write("    <span aria-hidden=\"true\">&times;</span>\r\n");
      out.write("  </button>\r\n");
      out.write("  </div>\r\n");

	session.removeAttribute("errorPage");
}

      out.write("\r\n");
      out.write("<div class=\"container-fluid\">\r\n");
      out.write("<div class=\"row mt-5\">\r\n");
      out.write("<div class=\"col-md-4 offset-md-4\">\r\n");
      out.write("<div class=\"card\">\r\n");
      out.write("<div class=\"card-body px-5\">\r\n");
      out.write("<center><img src=\"img/add-friend.png\" style=\"height:80px; width:80px;\"></center>\r\n");
Employee employee=(Employee)session.getAttribute("current-employee");
Employee editEmployee=(Employee)request.getAttribute("employee");
if(editEmployee==null||employee==null){
	
      out.write("\r\n");
      out.write("<form action=\"RegisterServlet\" method=\"post\">\r\n");
      out.write("<h3 class=\"text-center my-3\">Sign Up Here</h3>\r\n");
      out.write("<div class=\"form-group\">\r\n");
      out.write("<fieldset class=\"form-group\">\r\n");
      out.write("<label>Name</label><input type=\"text\" class=\"form-control\" placeholder=\"Enter here\" name=\"empName\" required=\"required\">\r\n");
      out.write("</fieldset>\r\n");
      out.write("    </div>\r\n");
      out.write("<div class=\"form-group\">\r\n");
      out.write("    <label for=\"email\">E-mail Address</label>\r\n");
      out.write("    <input name=\"empEmail\" type=\"email\" class=\"form-control\" placeholder=\"Enter here\" aria-describedby=\"emailInput\" required=\"required\">\r\n");
      out.write("  </div> \r\n");
      out.write("<div class=\"form-group\">\r\n");
      out.write("    <label for=\"password\">Password</label>\r\n");
      out.write("    <input name=\"empPassword\" type=\"password\" class=\"form-control\" placeholder=\"Enter here\" aria-describedby=\"passwordInput\">\r\n");
      out.write("  </div>\r\n");
      out.write(" <div class=\"form-group\">\r\n");
      out.write("    <label for=\"joindate\">Join Date</label>\r\n");
      out.write("    <input name=\"empJoinDate\" type=\"date\" class=\"form-control\" placeholder=\"Enter here\" aria-describedby=\"joinDateInput\">\r\n");
      out.write("  </div>\r\n");
      out.write(" <div class=\"form-group\">\r\n");
      out.write("    <label for=\"salary\">Salary</label>\r\n");
      out.write("    <input name=\"empSalary\"  type=\"number\" class=\"form-control\" placeholder=\"Enter here\" aria-describedby=\"salarynput\">\r\n");
      out.write("  </div>\r\n");
      out.write("  ");
 if(employee == null)
{

      out.write("\r\n");
      out.write("  <a href=\"login.jsp\" class=\"text-center d-block mb-2\">Already Registered? Click here</a>\r\n");
      out.write("  ");

}
  
      out.write("\r\n");
      out.write(" <div class=\"container text-center\">\r\n");
      out.write(" <button class=\"btn btn-outline-success\">Save</button>\r\n");
      out.write(" <button class=\"btn btn-outline-warning\">Reset</button>\r\n");
      out.write(" </div>\r\n");
      out.write("</form>\r\n");

} else{
	
      out.write("\r\n");
      out.write("<form action=\"EmployeeServlet?action=update&empId=");
      out.print(editEmployee.getEmpId());
      out.write("\" method=\"post\">\r\n");
      out.write("<h3 class=\"text-center my-3\">Edit Info</h3>\r\n");
      out.write("<div class=\"form-group\">\r\n");
      out.write("<label for=\"name\">Name</label>\r\n");
      out.write("<input type=\"text\" value=\"");
      out.print(editEmployee.getEmpName() );
      out.write("\" class=\"form-control\" name=\"empName\" required=\"required\">\r\n");
      out.write("    </div>\r\n");
      out.write("<div class=\"form-group\">\r\n");
      out.write("    <label for=\"email\">E-mail Address</label>\r\n");
      out.write("    <input name=\"empEmail\" type=\"email\" class=\"form-control\" value=\"");
      out.print(editEmployee.getEmpEmail() );
      out.write("\" placeholder=\"Enter here\" aria-describedby=\"emailInput\" required=\"required\">\r\n");
      out.write("  </div> \r\n");
      out.write("<div class=\"form-group\">\r\n");
      out.write("    <label for=\"password\">Password</label>\r\n");
      out.write("    <input name=\"empPassword\" type=\"password\" class=\"form-control\" value=\"");
      out.print(editEmployee.getEmpPassword() );
      out.write("\" placeholder=\"Enter here\" aria-describedby=\"passwordInput\">\r\n");
      out.write("  </div>\r\n");
      out.write(" <div class=\"form-group\">\r\n");
      out.write("    <label for=\"joindate\">Join Date</label>\r\n");
      out.write("    <input name=\"empJoinDate\" type=\"date\" class=\"form-control\" value=\"");
      out.print(editEmployee.getEmpJoinDate() );
      out.write("\" placeholder=\"Enter here\" aria-describedby=\"joinDateInput\">\r\n");
      out.write("  </div>\r\n");
      out.write(" <div class=\"form-group\">\r\n");
      out.write("    <label for=\"salary\">Salary</label>\r\n");
      out.write("    <input name=\"empSalary\"  type=\"number\" class=\"form-control\" value=\"");
      out.print(editEmployee.getEmpSalary() );
      out.write("\" placeholder=\"Enter here\" aria-describedby=\"salarynput\">\r\n");
      out.write("  </div>\r\n");
      out.write(" <div class=\"container text-center\">\r\n");
      out.write(" <button class=\"btn btn-outline-success\">Save</button>\r\n");
      out.write(" <button class=\"btn btn-outline-warning\">Reset</button>\r\n");
      out.write(" </div>\r\n");
      out.write("</form>\r\n");

}

      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
