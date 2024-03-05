package coder.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import coder.daos.OrderDAO;
import coder.daos.ProductDAO;
import coder.models.CartItem;
import coder.models.Order;
import coder.models.Product;
import coder.models.User;


@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection conn=null;
    private DataSource ds=null;
   
    public CartController() {
               
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn=null;
		try {
			conn=ds.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String action=request.getParameter("action");
		if(action.contentEquals("billout")) {
				
				PrintWriter pw=response.getWriter();
				String items=request.getParameter("items");
				
				
				ProductDAO pdao=new ProductDAO();
				
				Gson gson=new Gson();
				CartItem []citems=gson.fromJson(items, CartItem[].class);
				
				
				
				List<Product> products=new ArrayList<Product>();
				
				for(CartItem c: citems) {
					products.add(pdao.getSingleProductById(c.getId(), conn));
				}
				
				pw.write(gson.toJson(products));
				
			}else if(action.contentEquals("orderout")) {
				orderOut(request,response,conn);
			}
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
	private void orderOut(HttpServletRequest request, HttpServletResponse response, Connection conn) throws IOException {
		String items=request.getParameter("items");
		PrintWriter pw=response.getWriter();
		OrderDAO orderDao=new OrderDAO();
		User user=(User) request.getSession().getAttribute("user");
		
		boolean bol=orderDao.insertNewOrder(conn, user.getId(),items);
		if(bol)
			pw.write("success");
		else pw.write("error");
			
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
