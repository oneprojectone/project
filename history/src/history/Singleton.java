package history;
import java.sql.Connection;
import java.sql.DriverManager;

public class Singleton {
	static Connection con;
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
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
