package product.service;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionProvider;
import product.dao.ProductDao;
import product.model.Product;


public class ProductSearchService {


	private ProductDao productDao = new ProductDao();
	
	
	public Product SearchProduct(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			
			Product product = productDao.selectById(conn, pageNum);
			System.out.println(product);
			return product;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
	}
	
	
}
