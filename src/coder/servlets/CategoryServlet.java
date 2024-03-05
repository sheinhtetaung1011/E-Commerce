package coder.servlets;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import coder.daos.CategoryDAO;
import coder.models.Category;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds=null;    
    
    public CategoryServlet() {
        super();
        
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException{
    	try {
			InitialContext initcontext=new InitialContext();
			Context env=(Context) initcontext.lookup("java:comp/env");
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
			
				CategoryDAO catdao=new CategoryDAO();
				List<Category> categories=catdao.getAllCat(conn);
				
				request.setAttribute("categories", categories);
				request.getRequestDispatcher("index.jsp").forward(request, response);
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
