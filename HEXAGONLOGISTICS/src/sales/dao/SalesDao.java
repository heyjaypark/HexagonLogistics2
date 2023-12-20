
package sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import product.model.Product;
import sales.model.RegistRequest;
import sales.model.Sales;
import sales.model.SalesList;

public class SalesDao {

	public Product selectByP_no(Connection conn, String p_no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from product_list where p_no = ?");
			pstmt.setString(1, p_no);
			rs = pstmt.executeQuery();
			Product product = null;
			if (rs.next()) {
				product = new Product(rs.getInt("p_no"), 
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

	public void insert(Connection conn, RegistRequest sal) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("insert into sales_list values(S_SALES_LIST.NEXTVAL,?,?,?,?,?)")) {
			pstmt.setInt(1, sal.getP_no());
			pstmt.setInt(2, sal.getS_seoul());
			pstmt.setInt(3, sal.getS_suwon());
			pstmt.setInt(4, sal.getS_incheon());
			pstmt.setDate(5, sal.getS_date());
			pstmt.executeUpdate();
		}
	}

	public void update(Connection conn, RegistRequest salesReq) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update PRODUCT_LIST set P_SEOUL=P_SEOUL-?, P_SUWON=P_SUWON-?,P_INCHEON=P_INCHEON-? where P_NO=?")) {
			pstmt.setInt(1, salesReq.getS_seoul());
			pstmt.setInt(2, salesReq.getS_suwon());
			pstmt.setInt(3, salesReq.getS_incheon());
			pstmt.setInt(4, salesReq.getP_no());
			pstmt.executeUpdate();
		}

	}
	
	public List<SalesList> select1(Connection conn, int startRow, int size) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SalesList> result = new ArrayList<>();
		try {

			pstmt = conn.prepareStatement("select A.num, s_num, A.p_no, B.p_name, s_seoul, s_suwon, s_incheon, s_date,B.price from (select rownum as num, sales_list.* from sales_list) A, product_list B  where A.p_no = B.p_no and A.num between ? and ? order by s_num");
				  pstmt.setInt(1, startRow);
				  pstmt.setInt(2, size);
				  rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result.add(convertSales(rs));
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
			rs = stmt.executeQuery("select count(*) from sales_list");

			if (rs.next()) {
				return rs.getInt(1);
				

			}
			return 0;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);

		}
	}
	
	private SalesList convertSales(ResultSet rs) throws SQLException {
		return new SalesList(rs.getInt("s_num"),
				rs.getInt("p_no"),
				rs.getString("p_name"),
				rs.getInt("s_seoul"),
				rs.getInt("s_suwon"),
				rs.getInt("s_incheon"),
				rs.getString("s_date"),
				rs.getInt("price")); 

	}
	
	/*
	 * p_no로 검색했을 때 총 개수를 볼 수 있는 DAO
	 * */
	public int searchCount(Connection conn, int code) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement("select count(*) from sales_list where p_no = ?");			
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {			
			
				return rs.getInt(1);
				
			}				
			
			return 0;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);

		}
	}

public List<SalesList> select2(Connection conn, int startRow, int size, int code) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SalesList> result = new ArrayList<>();
		try {

			pstmt = conn.prepareStatement("select s_num, sale.p_no, product_list.p_name, s_seoul, s_suwon, s_incheon, s_date,B.price from (select ROWNUM as num, sales_list.* from sales_list where p_no=?) SALE, product_list where product_list.p_no = sale.p_no and sale.num between ? and ? order by s_num");
				  pstmt.setInt(1, code);
				  pstmt.setInt(2, startRow);
				  pstmt.setInt(3, size);				  
				  rs = pstmt.executeQuery();
			
			while (rs.next()) {
				result.add(convertSales(rs));
			}
			System.out.println("ProductDao3" + startRow);
			System.out.println("ProductDao3" + size);
			
			return result;
			
			
	
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);

		}
	}

public int searchCount2(Connection conn, int code) throws SQLException {
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		stmt = conn.createStatement();
		pstmt = conn.prepareStatement("select count(*) from sales_list where s_num = ?");			
		pstmt.setInt(1, code);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {			
		
			return rs.getInt(1);
			
		}				
		
		return 0;

	} finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(stmt);

	}
}

public List<SalesList> select3(Connection conn, int startRow, int size, int code) throws SQLException {
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	List<SalesList> result = new ArrayList<>();
	try {

		pstmt = conn.prepareStatement("select s_num, sale.p_no, product_list.p_name, s_seoul, s_suwon, s_incheon, s_date,B.price from (select ROWNUM as num, sales_list.* from sales_list where s_num=?) SALE, product_list where product_list.p_no = sale.p_no and sale.num between ? and ? order by s_num");
			  pstmt.setInt(1, code);
			  pstmt.setInt(2, startRow);
			  pstmt.setInt(3, size);				  
			  rs = pstmt.executeQuery();
		
		while (rs.next()) {
			result.add(convertSales(rs));
		}
		System.out.println("ProductDao3" + startRow);
		System.out.println("ProductDao3" + size);
		
		return result;
		
		

	} finally {
		JdbcUtil.close(rs);
		JdbcUtil.close(pstmt);

	}
}
	
	
	

}
