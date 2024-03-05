package coder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coder.models.Category;

public class CategoryDAO {

	public CategoryDAO() {
		
	}
	
	public List<Category> getAllCat(Connection conn){
		List<Category> categories=new ArrayList<>();
		String query="SELECT * FROM categories";
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				categories.add(new Category(rs.getInt("id"),
								rs.getString("name"),
								rs.getString("image")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return categories;
	}
}
