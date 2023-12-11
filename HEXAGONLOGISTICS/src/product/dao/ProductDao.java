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


public class ProductDao {
	
	public List<Product> select1(Connection conn, int startRow, int size) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> result = new ArrayList<>();
		try {

			pstmt = conn.prepareStatement("SELECT * FROM (SELECT ROWNUM AS NUM, product_list.* FROM product_list)WHERE NUM BETWEEN ? AND ? order by p_no");
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


	
	
	public Product selectById(Connection conn, int p_no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement("select * from product_list where p_no = ?");
			pstmt.setInt(1, p_no);
			rs = pstmt.executeQuery();

					
			Product product = null;
			if(rs.next()) {
				product = new Product(
						rs.getInt("p_no"),
						rs.getString("p_name"),
						rs.getInt("p_seoul"),
						rs.getInt("p_suwon"),
						rs.getInt("p_incheon"),
						rs.getInt("price")
						);
							

			}
			return product;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	
	}

	public void insert(Connection conn, Product mem) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement("insert into product_list values(?,?,?,?,?,?)")) {
			pstmt.setInt(1, mem.getP_no());
			pstmt.setString(2, mem.getP_name());
			pstmt.setInt(3, mem.getP_seoul());
			pstmt.setInt(4, mem.getP_suwon());
			pstmt.setInt(5, mem.getP_incheon());
			pstmt.setInt(6, mem.getPrice());
		}}


	


	public void update(Connection conn, Product member) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update porduct_list set p_seoul = ?,p_suwon=?, p_incheon = ? where p_no = ?")) {
			pstmt.setInt(1, member.getP_seoul());
			pstmt.setInt(2, member.getP_suwon());
			pstmt.setInt(3, member.getP_incheon());
			pstmt.setInt(3, member.getP_no());

			pstmt.executeUpdate();
		}}


	

}
