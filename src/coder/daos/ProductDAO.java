package coder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coder.models.Product;

public class ProductDAO {

	public ProductDAO() {

	}

	public List<Product> getAllProductById(int cat_id, Connection conn) {
		List<Product> products = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM products WHERE cat_id=?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, cat_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				products.add(new Product(rs.getInt("id"), rs.getInt("cat_id"), rs.getInt("price"), rs.getString("name"),
						rs.getString("image"), rs.getString("description")));
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
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return products;
	}

	public Product getSingleProductById(int id, Connection conn) {
		Product product = null;

		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "SELECT * FROM products WHERE id=?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("id"), rs.getInt("cat_id"), rs.getInt("price"), rs.getString("name"),
						rs.getString("image"), rs.getString("description"));
			}

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
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return product;
	}

	public Product getProductByIdWithUNameAndCount(int id, String username, int pcount, Connection conn) {
		Product product = null;

		ResultSet rs = null;
		PreparedStatement ps = null;
		String query = "SELECT * FROM products WHERE id=?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product(rs.getInt("id"), rs.getInt("cat_id"), pcount, rs.getInt("price"),
						rs.getString("name"), rs.getString("image"), rs.getString("description"), username);
			}

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
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return product;
	}

	public boolean addProductToDB(Connection conn,Product product) {
		boolean bol=false;
		String query="INSERT INTO products (cat_id,name,image,price,description) VALUES(?,?,?,?,?) ";
		PreparedStatement ps=null;
		int result=0;
		try {
			ps=conn.prepareStatement(query);
			ps.setInt(1,product.getCat_id());
			ps.setString(2,product.getName());
			ps.setString(3,product.getImage());
			ps.setInt(4,product.getPrice());
			ps.setString(5,product.getDescription());
			result=ps.executeUpdate();
			
			if(result==1)
				bol=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ps!=null)
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return bol;
	}
}
