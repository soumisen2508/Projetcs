package mycart.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import mycart.dao.CategoryDao;
import mycart.dao.ProductDao;
import mycart.entities.Category;
import mycart.entities.Product;
import mycart.helper.FactoryProvider;

@MultipartConfig
public class ProductOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductOperationServlet() {
        super();
    }
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out=response.getWriter()){
			//servlet decides whether to add category or product
			String op = request.getParameter("operation");
			if(op.trim().equals("addcategory")) {
				
				//add category
				//fetching category data
				
				String title = request.getParameter("catTitle");
				String description = request.getParameter("catDescription");
				Category category = new Category();
				category.setCategoryTitle(title);
				category.setCategoryDescription(description);
				//save category object to database
				CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
				int catId = categoryDao.saveCategory(category);
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("message", "Category "+catId+" added successfully!!");
				response.sendRedirect("admin.jsp");
				return;
				
				
			}else if(op.trim().equals("addproduct")){
				//add product
				
				String pName = request.getParameter("pName");
				String pDesc = request.getParameter("pDesc");
				int pPrice = Integer.parseInt(request.getParameter("pPrice"));
				int pDiscount = Integer.parseInt(request.getParameter("pDiscount"));
				int pQuantity = Integer.parseInt(request.getParameter("pQuantity"));
				int catId = Integer.parseInt(request.getParameter("catId"));
				Part part = request.getPart("pPic");
				
				Product p = new Product();
				p.setpName(pName);
				p.setpDesc(pDesc);
				p.setpPrice(pPrice);
				p.setpDiscount(pDiscount);
				p.setpQuantity(pQuantity);
				p.setpPhoto(part.getSubmittedFileName());
				
				//get category by catId
				CategoryDao cdao = new CategoryDao (FactoryProvider.getFactory());
				Category category = cdao.getCategoryById(catId);
				p.setCategory(category);
				
				//save product in DB
				ProductDao pdao = new ProductDao(FactoryProvider.getFactory());
				pdao.saveProduct(p);
				
				//photo upload
				//find out path to upload photo
				String path = request.getRealPath("img")+File.separator+"products"+File.separator+part.getSubmittedFileName();
				System.out.println(path);
				try {
				
				//photo uploading code
				FileOutputStream fos = new FileOutputStream(path);
				InputStream is = part.getInputStream();
				
				//reading data
				byte []data =new byte[is.available()];
				is.read(data);
				
				//writing data
				fos.write(data);
				fos.close();
				
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("message", "Product is added successfully!!");
				response.sendRedirect("admin.jsp");
				return;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
}

}
