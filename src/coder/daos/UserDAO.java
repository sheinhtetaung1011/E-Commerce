package coder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import coder.models.User;

public class UserDAO {
	User user = null;

	public UserDAO() {

	}

	public User getUserByEmail(Connection conn, String email) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM users WHERE email=?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getInt("role"), rs.getString("name"), rs.getString("email"),
						rs.getString("password"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return user;
	}

	public boolean AddUsertoDB(Connection conn, int role, String name, String email, String password) {
		boolean bol = false;
		PreparedStatement ps = null;

		String query = "INSERT INTO users(role,name,email,password) VALUES(?,?,?,?)";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, role);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			int result = ps.executeUpdate();
			if (result == 1)
				bol = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return bol;
	}

	
	public User getUserById(Connection conn, int user_id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM users WHERE id=?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getInt("role"), rs.getString("name"), rs.getString("email"),
						rs.getString("password"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return user;
	}
}
