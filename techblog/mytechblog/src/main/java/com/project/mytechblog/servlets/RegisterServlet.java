package com.project.mytechblog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.mytechblog.dao.UserDao;
import com.project.mytechblog.entities.User;
import com.project.mytechblog.helper.ConnectionProvider;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RegisterServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String check = request.getParameter("user_check");
			if(check == null) {
				
				out.println("Please Agree To Our Terms And Conditions.");
				
			}else {
				
				// extract rest of the data
				
				String name = request.getParameter("user_name");
				String email = request.getParameter("user_email");
				String password = request.getParameter("user_password");
				String gender = request.getParameter("gender");
				String about = request.getParameter("user_about");
				
				//create an user object to set all the data
				
				User user = new User(name, email, password, gender, about);
				
				//create an UserDao object
				
				UserDao dao = new UserDao(ConnectionProvider.getConnection());
				if(dao.saveUser(user)) {
					out.println("done");
				}else {
					out.println("error");
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
