
package coder.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import coder.daos.CategoryDAO;
import coder.daos.ProductDAO;
import coder.models.Category;
import coder.models.Product;


@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private DataSource ds=null;
    
    public ProductServlet() {
        super();
        
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException{
    	InitialContext initcontext = null;
		try {
			initcontext = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Context env = null;
		try {
			env = (Context) initcontext.lookup("java:comp/env");
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	try {
			ds=(DataSource) env.lookup("jdbc/testDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn=null;
		try {
			conn=ds.getConnection();
			String cat_id=request.getParameter("cat_id");
			
			ProductDAO pdao=new ProductDAO();
			List<Product> products=pdao.getAllProductById(Integer.parseInt(cat_id), conn);
			
			
			request.setAttribute("products", products);
			request.getRequestDispatcher("product.jsp").forward(request, response);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
