package mycart.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;

import mycart.dao.CategoryDao;
import mycart.dao.OrderDao;
import mycart.dao.ProductDao;
import mycart.dao.UserDao;
import mycart.entities.Category;
import mycart.entities.OrderItem;
import mycart.entities.Product;
import mycart.entities.User;
import mycart.helper.FactoryProvider;

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CheckOutServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			User user=(User)request.getSession().getAttribute("current-user");
			if(user!=null) {
				OrderItem order = new OrderItem();
				
				//get user by userId
				
				UserDao udao = new UserDao (FactoryProvider.getFactory());
				User user1 = udao.getUserById(user.getUserId());
				order.setUser(user1);
				order.setDate(formatter.format(date));
				
				//save order in DB
				
				OrderDao odao = new OrderDao(FactoryProvider.getFactory());
				odao.saveOrder(order);
				
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("message", "Order Id of #"+order.getOrderId()+ "is generated successfully!!");
				response.sendRedirect("index.jsp");
				return;
				
			}else {

				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("errorPage", "Sorry, we can't process your order now");
				response.sendRedirect("index.jsp");
			}
			

			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
