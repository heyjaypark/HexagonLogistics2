package sales.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import connection.ConnectionProvider;
import product.dao.ProductDao;
import product.model.Product;

public class SelectSalesService {

	private ProductDao productDao = new ProductDao();
	
	public Product selectProduct(int p_no) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Product product = productDao.selectById(conn, p_no);
			return product;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
	}
}
