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

import com.project.mytechblog.dao.PostDao;
import com.project.mytechblog.entities.Post;
import com.project.mytechblog.entities.User;
import com.project.mytechblog.helper.ConnectionProvider;
import com.project.mytechblog.helper.Helper;


@WebServlet("/AddPostServlet")
@MultipartConfig
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddPostServlet() {
        super();   
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			int catId = Integer.parseInt(request.getParameter("cId"));
			String postTitle = request.getParameter("postTitle");
			String postContent = request.getParameter("postContent");
			String postCode = request.getParameter("postCode");
			Part part = request.getPart("postPic");
			
			//getting current user id
			
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("currentUser");
			
			//out.println("your pic title is" + part.getSubmittedFileName());
			
			Post p = new Post(postTitle, postContent, postCode, part.getSubmittedFileName(), null, catId, user.getUserId());
			PostDao dao = new PostDao(ConnectionProvider.getConnection());
			if(dao.savePost(p)) {
				String path = request.getRealPath("/")+"blog_pics"+File.separator+part.getSubmittedFileName();
				Helper.saveFile(part.getInputStream(), path);
				//out.println("done");
				
			}else {
				out.println("error");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
