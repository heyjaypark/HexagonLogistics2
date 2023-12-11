package product.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionProvider;
import product.dao.ProductDao3;
import product.model.Product;


public class ProductListService {


	private ProductDao3 productDao = new ProductDao3();
	private int size = 10;
	
	public ProductPage getProductPage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int total = productDao.selectCount(conn);
			System.out.println("productListService:" +pageNum);
			
			List<Product> content = productDao.select1(conn, (pageNum-1)*size, size);
			return new ProductPage(total,pageNum,size,content);
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
			
		}
	}
	
	
}
