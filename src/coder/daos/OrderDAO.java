package coder.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coder.models.Order;

public class OrderDAO {

	public OrderDAO() {
		
	}
	
	public boolean insertNewOrder(Connection conn,int user_id,String orders) {
		boolean bol=false;
		String query="INSERT INTO orders (user_id,orders) VALUES(?,?)";
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(query);
			ps.setInt(1, user_id);
			ps.setString(2, orders);
			int result=ps.executeUpdate();
			if(result==1) {
				bol=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return bol;
	}
	
	
	public List<Order> getOrdersByUId(Connection conn,int user_id){
		List<Order> orders=new ArrayList<>();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query="SELECT * FROM orders WHERE user_id=?";
		try {
			ps=conn.prepareStatement(query);
			ps.setInt(1, user_id);
			rs=ps.executeQuery();
			while(rs.next()) {
				orders.add(new Order(
						rs.getInt("id"),
						rs.getInt("user_id"),
						rs.getString("orders")
								));
			}
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
		
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		return orders;
	}

}
