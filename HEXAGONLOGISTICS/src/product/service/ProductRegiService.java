package product.service;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionProvider;
import jdbc.JdbcUtil;
import member.service.DuplicateIdException;
import product.dao.ProductRegiDao;
import product.model.ProductRequest;

public class ProductRegiService {

	private ProductRegiDao prd = new ProductRegiDao();

	public void productregi(ProductRequest pro) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			int product = prd.selectById(conn, pro.getP_no());
			if (product != 0) {

				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}

			prd.insert(conn, pro);
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
