package coder.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

import coder.daos.OrderDAO;
import coder.daos.ProductDAO;
import coder.daos.UserDAO;
import coder.models.CartItem;
import coder.models.Order;
import coder.models.Product;
import coder.models.User;

@WebServlet("/AdminUserController")
public class AdminUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds = null;

	public AdminUserController() {
		super();

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initcontext = new InitialContext();
			Context env = (Context) initcontext.lookup("java:comp/env");
			ds = (DataSource) env.lookup("jdbc/testDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String action = request.getParameter("action");
		if (action.contentEquals("orderdetail")) {
			String user_id = request.getParameter("user_id");

			OrderDAO orderdao = new OrderDAO();
			ProductDAO pdao=new ProductDAO();
			UserDAO udao=new UserDAO();
			List<Order> orders = new ArrayList<>();
			List<CartItem> cartList = new ArrayList<>();
			List<Product> products=new ArrayList<>();
			Gson gson = new Gson();
			
			orders = orderdao.getOrdersByUId(conn, Integer.parseInt(user_id));
			for (Order order : orders) {
				CartItem[] cartitems = gson.fromJson(order.getOrders(), CartItem[].class);
				for (CartItem cartitem : cartitems) {
					cartList.add(cartitem);
				}
			}
			
			User user=udao.getUserById(conn, Integer.parseInt(user_id));
			
			for (CartItem item : cartList) {
				Product product=pdao.getProductByIdWithUNameAndCount(
						item.getId(), user.getName(), item.getCount(), conn);
				products.add(product);
			}

			request.setAttribute("products", products);
			request.getRequestDispatcher("/order_detail.jsp").forward(request, response);
		}
		
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
