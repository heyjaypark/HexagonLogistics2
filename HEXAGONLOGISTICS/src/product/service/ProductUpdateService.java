package product.service;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionProvider;
import jdbc.JdbcUtil;
import member.service.DuplicateIdException;
import product.dao.ProductUpdateDao;
import product.model.Product;
import product.model.ProductRequest;

public class ProductUpdateService {

	private ProductUpdateDao prd = new ProductUpdateDao();

	public void productUpdate(ProductRequest pro) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Product product = prd.selectById(conn, pro.getP_no());
		

			prd.update(conn,pro);
			conn.commit();
		} catch (SQLException e) {

			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
