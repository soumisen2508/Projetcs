package com.project.mytechblog.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.project.mytechblog.dao.UserDao;
import com.project.mytechblog.entities.Message;
import com.project.mytechblog.entities.User;
import com.project.mytechblog.helper.ConnectionProvider;
import com.project.mytechblog.helper.Helper;


@WebServlet("/EditServlet")
@MultipartConfig
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public EditServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			
			//fetch user data
			
			String userEmail = request.getParameter("user_email");
			String userName = request.getParameter("user_name");
			String userPassword = request.getParameter("user_password");
			String userAbout = request.getParameter("user_about");
			Part part = request.getPart("user_image");
			String imageName = part.getSubmittedFileName();
			
			//get the user from the session
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("currentUser");
			user.setUserName(userName);
			user.setUserEmail(userEmail);
			user.setUserAbout(userAbout);
			user.setUserPassword(userPassword);
			String oldFile = user.getUserProfile();
			user.setUserProfile(imageName);
			
			//update user data in database
			
			UserDao userDao = new UserDao(ConnectionProvider.getConnection());
			boolean ans = userDao.updateUser(user);
			if(ans) {
				String path = request.getRealPath("/")+"profile pics"+File.separator+user.getUserProfile();
				
				//code for deleting file 
				String oldFilePath = request.getRealPath("/")+"profile pics"+File.separator+oldFile;
				if(!oldFile.equals("default.png")){
					Helper.deleteFile(oldFilePath);
				}
				
					if(Helper.saveFile(part.getInputStream(), path)) {

						Message msg = new Message("Profile Details Updated Successfully.", "success", "alert-success");
						session.setAttribute("msg", msg);
				}
			}else {
				Message msg = new Message("Something Went Wrong. Please Try Again.", "error", "alert-danger");
				response.sendRedirect("login_page.jsp");
				session.setAttribute("msg", msg);
			}
			response.sendRedirect("profile.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
