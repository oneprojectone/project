package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Singleton {
	static Connection con;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String user = "lion";
	String pass = "1234";
	String driver = "oracle.jdbc.driver.OracleDriver";
	

	private Singleton() throws Exception {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
		
	}
	
	public static Connection getConnection() throws Exception{
		if (con == null) {
			Singleton sg = new Singleton();
		}
		return con;
	}
	
	public static void close() throws Exception{
		if (con != null) {
			if (!con.isClosed())
				con.close();
		}
		con = null;
	}
	
	public static void close(Statement stmt) throws Exception{
		if (stmt != null) {
			if (!stmt.isClosed())
				stmt.close();	
		}
		stmt = null;
	}
	
	public static void close(PreparedStatement pstmt) throws Exception{
		if (pstmt != null) {
			if (!pstmt.isClosed())
				pstmt.close();
		}
		pstmt = null;
	}
	
	public static void close(ResultSet rs) throws Exception{
		if (rs != null) {
			if (!rs.isClosed())
				rs.close();
		}
		rs = null;
	}
}
