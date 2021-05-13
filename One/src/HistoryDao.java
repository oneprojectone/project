import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class HistoryDao {
	
	   Connection con=null;
	   PreparedStatement pstmt=null; 
	   ResultSet rs=null;
	
	Object ob[][]=new Object[0][7];
	   DefaultTableModel model;
	   JTable table;
	   JScrollPane js;
	   String str[]= {"NO", "ID", "MENU", "SIZE", "OPTION", "PRICE", "DATE"};
	   
	   private void connect() {
	       try {
	         
	           Class.forName("oracle.jdbc.driver.OracleDriver");
	           String url="jdbc:oracle:thin:@localhost:1521:xe";
	           con=DriverManager.getConnection(url, "lion", "1234");
	           System.out.println("���� : "+con);
	       }catch(Exception e) {
	           System.out.println("DB ���� ���� : "+e);
	       }
	   }
	  public void select() {
	       try { 
	           String sql="select * from historytest order by hno"; 
	           pstmt=con.prepareStatement(sql);
	           System.out.println("pstmt : "+pstmt);
	           rs=pstmt.executeQuery(); // select����
	           System.out.println("rs : "+rs);
	           
	      
	           while (rs.next()) {
	               Object[] data =
	                  {
	                        rs.getInt("hno"),
	                        rs.getString("hid"),
	                        rs.getString("hmenu"),
	                        rs.getString("hsize"),
	                        rs.getString("hoption"),
	                        rs.getString("hprice"),
	                        rs.getString("hdate")
	                  };
	               model.addRow(data);
	               
	               for (int i = 0; i < 7; i++) {
	                  System.out.print(data[i] + "\t");
	               }
	               System.out.println();
	            }
	           
	       }catch(Exception e) {
	           System.out.println("select() ���� ���� : " +e);
	       }
	   }
	
	}
