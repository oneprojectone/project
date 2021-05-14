package one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConn {
	private static Connection dbConn;

	public static Connection getConnection() {
		if (dbConn == null) {
			try {
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "one";
				String pwd = "1234";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return dbConn;
	}

	public static Connection getConnection(String url, String user, String pwd) {
		if (dbConn == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return dbConn;
	}

	public static void close() {
		if (dbConn != null) {
			try {
				if (!dbConn.isClosed())
					dbConn.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		dbConn = null;
	}
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				if (!stmt.isClosed())
					stmt.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		stmt = null;
	}
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				if (!pstmt.isClosed())
					pstmt.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		pstmt = null;
	}
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		rs = null;
	}
}
