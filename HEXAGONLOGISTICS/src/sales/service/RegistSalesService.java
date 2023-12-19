package sales.service;

import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionProvider;
import jdbc.JdbcUtil;
import sales.dao.SalesDao;
import sales.model.RegistRequest;

public class RegistSalesService {
	
	private SalesDao salesDao = new SalesDao();
	
	public void registSales(RegistRequest salesReq) throws Exception {
		Connection conn = null;

		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			salesDao.insert(conn, salesReq);
			salesDao.update(conn, salesReq);
			conn.commit();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

}
