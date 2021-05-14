import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
   private static Connection dbConn;

   public static Connection getConnection() {
      if (dbConn == null) {
         try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String user = "one";
            String pw = "1234";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            dbConn = DriverManager.getConnection(url, user, pw);
            System.out.println("���� ����");
         } catch (Exception e) {
            System.out.println("���� ����");
         }
      }
      return dbConn;
   }
}