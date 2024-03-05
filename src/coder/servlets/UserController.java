package coder.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

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

import coder.daos.UserDAO;
import coder.models.User;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds = null;

	public UserController() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
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
			ds = (DataSource) env.lookup("jdbc/testDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		UserDAO userDao = new UserDAO();
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (action == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			switch (action) {
			case "billout":
				if (session.getAttribute("user") == null) {
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				} else
					request.getRequestDispatcher("/cartout.jsp").forward(request, response);
				break;
			case "login":
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				break;
			case "register":
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				break;
			case "formlogin":
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				User user = userDao.getUserByEmail(conn, email);

				if (user == null || !password.contentEquals(user.getPassword())) {
					request.setAttribute("msgerror", "Login error!please try again");
					request.getRequestDispatcher("/login.jsp").forward(request, response);

				}else if(user.getRole()==1){
					request.getRequestDispatcher("/admin_home.jsp").forward(request, response);
					
				}
				else {

					session.setAttribute("user", user);
					request.setAttribute("msgsuccess", "Succcessful login");
					request.getRequestDispatcher("/cartout.jsp").forward(request, response);
				}
				break;
			case "logout":
				session.removeAttribute("user");
				request.getRequestDispatcher("/index.jsp").forward(request, response);

				break;

			case "registerform":
				String name = request.getParameter("name");
				String remail = request.getParameter("email");
				String rpassword = request.getParameter("password");

				User ruser = userDao.getUserByEmail(conn, remail);
				if (ruser != null) {
					request.setAttribute("msgerror", "User Already exists!");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
				} else {
					if (userDao.AddUsertoDB(conn, 2, name, remail, rpassword)) {
						request.setAttribute("msgsuccess", "Successfully Register!Please login below");
						request.getRequestDispatcher("/login.jsp").forward(request, response);
					}
					break;

				}
			}
		}
		if (conn != null)
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
