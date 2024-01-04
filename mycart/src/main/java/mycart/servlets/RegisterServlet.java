package mycart.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mycart.entities.User;
import mycart.helper.FactoryProvider;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegisterServlet() {
        super();
    }

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		response.setContentType("text/html;character=UTF-8");
try(PrintWriter out = response.getWriter()){
			
			try {
				HttpSession httpSession =request.getSession();
				String userName = request.getParameter("user_name");
				String userEmail = request.getParameter("user_email");
				String userPassword = request.getParameter("user_password");
				String userPhone = request.getParameter("user_phone");
				String userAddress = request.getParameter("user_address");
				
				if(userName.isEmpty()) {
					
					httpSession.setAttribute("errorPage", "Please input your name again");
					response.sendRedirect("register.jsp");
				}
				
				else if(userEmail.isEmpty()) {
					httpSession.setAttribute("errorPage", "Please input your email again");
					response.sendRedirect("register.jsp");
				}
				
				else if(userPassword.isEmpty()) {
					httpSession.setAttribute("errorPage", "Please input your password again");
					response.sendRedirect("register.jsp");
				}
				
				else if(userPhone.isEmpty()) {
					httpSession.setAttribute("errorPage", "Please input your contact nnumber again");
					response.sendRedirect("register.jsp");
				}
				
				else if(userAddress.isEmpty()) {
					httpSession.setAttribute("errorPage", "Please input your address again");
					response.sendRedirect("register.jsp");
				}
				else {
				
				//Creating User object to store data
				
				User user = new User(userName, userEmail, userPassword, userPhone, "default.jpg", userAddress, "normal");
				Session hibernateSession=FactoryProvider.getFactory().openSession();
				Transaction tx = hibernateSession.beginTransaction();
				int userId = (int) hibernateSession.save(user);
				tx.commit();
				hibernateSession.close();
				httpSession.setAttribute("message", "Registration successfull! User Id is "+userId);
				response.sendRedirect("index.jsp");
				return;
				}
			}catch(Exception e) {
				e.printStackTrace();
				HttpSession httpSession =request.getSession();
				httpSession.setAttribute("errorPage", "Please try again! ");
				response.sendRedirect("register.jsp");
				return;
			}
			
		}
	}
		
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
}
}
