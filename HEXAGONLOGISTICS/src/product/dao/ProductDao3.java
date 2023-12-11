package product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import jdbc.JdbcUtil;
import product.model.Product;





public class ProductDao3 {


	public List<Product> select1(Connection conn, int startRow, int size) throws SQLException {
	
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> result = new ArrayList<>();
		try {

			pstmt = conn.prepareStatement("select * from product_list where p_no between ? and ? order by p_no");
				  pstmt.setInt(1, startRow);
				  pstmt.setInt(2, size);
				  rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result.add(convertProduct(rs));
			}
			System.out.println("ProductDao3" + startRow);
			System.out.println("ProductDao3" + size);
			
			return result;
			
			
	
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);

		}
	}
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from product_list");

			if (rs.next()) {
				return rs.getInt(1);
				

			}
			return 0;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);

		}
	}
	
	private Product convertProduct(ResultSet rs) throws SQLException {
		return new Product(rs.getInt("p_no"),
				rs.getString("p_name"),
				rs.getInt("p_seoul"),
				rs.getInt("p_suwon"),
				rs.getInt("p_incheon"),
				rs.getInt("price")); 

	}
	

}
