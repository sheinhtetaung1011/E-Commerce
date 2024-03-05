package coder.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import coder.daos.ProductDAO;
import coder.models.Product;

@WebServlet("/AdminProductController")
public class AdminProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds=null;

	public AdminProductController() {
		super();

	}
	
	 @Override
	    public void init(ServletConfig config) throws ServletException{
	    	InitialContext initcontext = null;
			try {
				initcontext = new InitialContext();
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	try {
				Context env=(Context) initcontext.lookup("java:comp/env");
				ds=(DataSource) env.lookup("jdbc/testDB");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Connection conn=null;
		try {
			conn=ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String action = request.getParameter("action");
		if (action.contentEquals("newproduct")) {
//			String cat_id = request.getParameter("category");
//			String name = request.getParameter("name");
//			String price = request.getParameter("price");
//			String description = request.getParameter("description");
			String name1 = null;
			
			String filePath = "C:\\JavaEE\\JavaEEWorkspace\\E-Commerce\\webContent\\assets\\uploads";
			List<FileItem> fileitems = new ServletFileUpload(new DiskFileItemFactory())
					.parseRequest(new ServletRequestContext(request));
			System.out.println("fileitem"+fileitems.size());
			for (FileItem item : fileitems) {
				if (!item.isFormField()) {
					
					 name1 = new File(item.getName()).getName();
					
					try {
						
						item.write(new File(filePath + File.separator + name1));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					String name = item.getFieldName();
				    String value = item.getString();
				   System.out.println(value);
					
				}
			}
			String image=filePath + File.separator + name1;
			System.out.println("imagepath "+image);
			Product product=new Product(Integer.parseInt(fileitems.get(0).getString()),Integer.parseInt(fileitems.get(3).getString()),
					fileitems.get(1).getString(),image,fileitems.get(4).getString());
			pw.write(product.toString());
			
			ProductDAO pdao=new ProductDAO();
			pdao.addProductToDB(conn, product);
//			
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
