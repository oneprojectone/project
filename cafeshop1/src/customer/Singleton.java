package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Singleton {
	static Connection con;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	String user = "one";
	String pass = "cafe";
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
}
