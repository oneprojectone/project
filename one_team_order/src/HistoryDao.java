import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HistoryDao {

	Connection con = DBConn.getConnection();

	Statement stmt;
	PreparedStatement pstmt; 
	ResultSet rs;
	String sql;
	
	HistoryDto dto = new HistoryDto();

	String data[][] = new String[0][7]; 
	String title[] = { "NO", "아이디", "상품명", "사이즈", "옵션", "가격", "주문날짜" };
	
	DefaultTableModel model = new DefaultTableModel(data, title);

	
	  public void dbConnect() {

	      
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	          con = DriverManager.getConnection(
	            "jdbc:oracle:thin:@localhost:1521:xe", "one", "1234");
	          System.out.println("연결 성공!");
	          
	          
	      } catch (Exception e) { System.out.println("연결 실패");
	      }
	      }
	
	
public void init(Order or) {
		
		or.txt1.setText("");
		or.txt2.setText("");
		or.txt3.setText("");
		or.txt4.setText("");
		or.txt5.setText("");
	}
	
	public HistoryDto selectByPk(Integer Num) throws Exception{
   
	       String sql = "SELECT * FROM historytest WHERE hno = " + Num;
	       stmt = con.createStatement();
	       rs = stmt.executeQuery(sql);
	       if( rs.next() )
	       {
	       
	          dto.setNO(rs.getString("hno"));
	          dto.setID(rs.getString("hid"));
	          dto.setMENU(rs.getString("hmenu"));
	          dto.setOPTION(rs.getString("hoption"));
	          dto.setSIZE(rs.getString("hsize"));
	          dto.setPRICE(rs.getString("hprice"));
	          dto.setDATE(rs.getString("hdate"));
	       }
	       rs.close();
	       stmt.close();
	       return dto;
}


	public void allview(DefaultTableModel model, Order order) {
		
		init(order);
		
		try {
			String sql = "select * from historytest";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			
			model.setRowCount(0);

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
		         System.out.println("모델에 데이타 확인");
	 
		         }
		
				
	} catch (Exception e) { System.out.println("전체 검색 실패"); 
	
	  } 
	      finally {
	      try { rs.close(); }
	      catch (SQLException e) { e.printStackTrace(); }
	      }
	}
	      
	
	
	 public void updateview(DefaultTableModel model, Order order) {
	      HistoryDto dtoup = new HistoryDto();
	      try {
	         

	            if(order.txt1.getText().length() == 0) {
	               JOptionPane.showMessageDialog(order, "수정하고 싶은 데이터를 목록에서 선택해주세요.");
	               return;
	            }
	            
	            String sql = "SELECT * FROM history where hid = '" + order.txt1.getText() + "'";
	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);
	            
	             
	             if( rs.next() )
	             {
	               
	                dtoup.setNO(rs.getString("hno"));
	                dtoup.setID(rs.getString("hid"));
	                dtoup.setMENU(rs.getString("hmenu"));
	                dtoup.setOPTION(rs.getString("hoption"));
	                dtoup.setSIZE(rs.getString("hsize"));
	                dtoup.setPRICE(rs.getString("hprice"));
	                dtoup.setDATE(rs.getString("hdate"));

	                if(dtoup.getMENU().trim().equals(order.txt2.getText().trim()) && dtoup.getSIZE().trim().equals(order.txt3.getText().trim()) && 
	                        dtoup.getOPTION().trim().equals(order.txt1.getText().trim())  && dtoup.getPRICE().trim().equals(order.txt5.getText().trim())) {

	                         JOptionPane.showMessageDialog(order, "상품명, 사이즈, 옵션, 가격만 수정할 수 있습니다. 수정할 내용을 입력해주세요");
	                         return;
	                  }
	                     
	                    
	                           
	                  String sqlup = "update history set hmenu = '" + order.txt2.getText()+ "', hsize = '" + order.txt3.getText()
	                        + "', hoption = '" + order.txt1.getText() + "', hprice = '" + order.txt5.getText() + "' where hid = '" + order.txt1.getText() + "'";
	                  pstmt = con.prepareStatement(sqlup);
	                  pstmt.executeUpdate(); 
	             }
	      		}	catch (Exception e) { System.out.println("수정 실패"); 
	               } 
	               
	               JOptionPane.showMessageDialog(order, "정보가 수정되었습니다.");
	               allview(model,order);
	 }
	 
	void deleteMyInfo(String id) {
		 try {
				sql="DELETE FROM historytest WHERE hno = ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				 }catch(Exception e) {
					 e.printStackTrace();
				 }
	 }
		
	


	
				 
	 
	 
}



