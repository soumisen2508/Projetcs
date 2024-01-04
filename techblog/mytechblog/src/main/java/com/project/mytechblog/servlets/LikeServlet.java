package com.project.mytechblog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.mytechblog.dao.LikeDao;
import com.project.mytechblog.helper.ConnectionProvider;


@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LikeServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String operation = request.getParameter("operation");
			int userId = Integer.parseInt(request.getParameter("userId"));
			int postId = Integer.parseInt(request.getParameter("postId"));
			
			// out.println("data from server");
			// out.println(operation);
			// out.println(userId);
			// out.println(postId);
			
			LikeDao dao = new LikeDao(ConnectionProvider.getConnection());
			
			if(operation.equals("like")) {
				boolean f = dao.insertLikeOnPost(postId, userId);
				out.println(f);
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
