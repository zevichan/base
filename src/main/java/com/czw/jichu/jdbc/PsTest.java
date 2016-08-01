package com.czw.jichu.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * 
 * @author Zevi Chan
 * @Date 2016年7月5日
 */
public class PsTest {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		ps();
	}

	static void ps() throws SQLException {
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			// conn = JdbcUtilsSing.getInstance().getConnection();

			String sql = "{ call addUser(?,?,?,?) } ";
			cs = conn.prepareCall(sql);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.setString(1, "ps name");
			cs.setDate(2, new java.sql.Date(System.currentTimeMillis()));
			cs.setFloat(3, 100f);

			cs.executeUpdate();

			int id = cs.getInt(4);

			System.out.println("id=" + id);
		} finally {
			JdbcUtils.free(rs, cs, conn);
		}
	}

}
