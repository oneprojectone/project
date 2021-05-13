import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HistoryDao {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt; 
	ResultSet rs;
	
	
	

	HistoryDto dto = new HistoryDto();
//	SelectFrame sfrdao = new SelectFrame();
	
	
	String data[][] = new String[0][7]; // 0은 addRow할 때 시작점, 7은 column 개수
	String title[] = { "NO", "아이디", "상품명", "사이즈", "옵션", "가격", "주문날짜" };
	
	DefaultTableModel model = new DefaultTableModel(data, title); 
	
	
	
	
	public void dbConnect() {
//		String driver = "oracle.jdbc.driver.OracleDriver";
//		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
//		String userid = "lion"; String passwd = "1234";

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", "lion", "1234");
		    System.out.println("연결 성공!");
		    
		    
		} catch (Exception e) { System.out.println("연결 실패");
		} /*finally {
			try { con.close(); }
			catch (SQLException e) { e.printStackTrace(); }
			}*/
		}
	
	public void init(Manager mg) {
		
		mg.txtNo.setText("");
//		txtNo.setEditable(false);
		mg.txtId.setText("");
//		txtId.setEditable(false);
		mg.txtName.setText("");
//		txtName.setEditable(false);
		mg.txtSize.setText("");
//		txtSize.setEditable(false);
		mg.txtIh.setText("");
//		txtIh.setEditable(false);
		mg.txtPrice.setText("");
//		txtPrice.setEditable(false);
		mg.txtDate.setText("");
//		txtDate.setEditable(false);
	}
	
	public HistoryDto selectByPk(Integer Num) throws Exception{
   
	       String sql = "SELECT * FROM history WHERE hno = " + Num;
	       stmt = con.createStatement();
	       rs = stmt.executeQuery(sql);
	       if( rs.next() )
	       {
	          dto.setNo(rs.getString("hno"));
	          dto.setName(rs.getString("hmenu"));
	          dto.setId(rs.getString("hid"));
	          dto.setOption(rs.getString("hoption"));
	          dto.setSize(rs.getString("hsize"));
	          dto.setPrice(rs.getString("hprice"));
	          dto.setDate(rs.getString("hdate"));
	          

	       }
	       
	       rs.close();
	       stmt.close();
	       
	       return dto;
	    }
	
	public void allview(DefaultTableModel model, Manager manager) {
		
		init(manager);
		
		try {
			String sql = "select * from history";
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
//		            System.out.println("모델에 데이타 붙여넣음");
	 
		         }
		
				
	} catch (Exception e) { System.out.println("전체 검색 실패"); 
	} 
		finally {
		try { rs.close(); }
		catch (SQLException e) { e.printStackTrace(); }
		}
		
		
	}
	
	public void mouseview(SelectFrame sfr) {
//		dbConnect();
		
		MouseListener mm = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	
	        	sfr.txtId.setEditable(false);
	        	sfr.txtName.setEditable(false);
	        	sfr.txtDate.setEditable(false); 
	        	
	        	
	        	if(e.getSource() == sfr.txtId) {
	        		sfr.txtId.setEditable(true);
	        		sfr.txtName.setText("");
	        		sfr.txtDate.setText("");
//	        		sql = "select * from history where hid = '" + sfr.txtId.getText() + "'";
//		    		System.out.println("sql 입력됨");
	        		
	        	} else if(e.getSource() == sfr.txtName) {
	        		sfr.txtId.setText("");
	        		sfr.txtName.setEditable(true);
	        		sfr.txtDate.setText("");
//	        		sql = "select * from history where hmenu = '" + sfr.txtName.getText() + "'";
	        		
	        	} else if(e.getSource() == sfr.txtDate) {
	        		sfr.txtId.setText("");
	        		sfr.txtName.setText("");
	        		sfr.txtDate.setEditable(true);
//	        		sql = "select * from history where hdate = '" + sfr.txtDate.getText() + "'";
	        	}
	        }
	   };
	   
	   sfr.txtId.addMouseListener(mm);
	   sfr.txtName.addMouseListener(mm);
	   sfr.txtDate.addMouseListener(mm);
		
	}
	
	
	public void searchview(DefaultTableModel model, SelectFrame sfr) {
		String sql = "";
		try {
			
			
//			if(str != null && !str.isEmpty())
			if (!(sfr.txtId.getText().length() == 0)) {
				sql = "select * from history where hid like '%" + sfr.txtId.getText() + "%'";
				System.out.println("아이디로 검색 실행");
			}
			else if (!(sfr.txtName.getText().length() == 0)) {
				sql = "select * from history where hmenu like '%" + sfr.txtName.getText() + "%'";
				System.out.println("상품명으로 검색 실행");
			}
			else if (!(sfr.txtDate.getText().length() == 0)) {
				sql = "select * from history where hdate like '%" + sfr.txtDate.getText() + "%'";
				System.out.println("날짜로 검색 실행");
			}
			
/*			if(sfr.txtId.getText().length() == 0 && sfr.txtName.getText().length() == 0 
					&& sfr.txtDate.getText().length() == 0) {
				 JOptionPane.showMessageDialog(this, "검색하고 싶은 데이터를 입력해주세요.");
				
			}
*/			
			System.out.println("0");
			pstmt = con.prepareStatement(sql);
			System.out.println("1" + sql);
			rs = pstmt.executeQuery(); 
			System.out.println("2");
			model.setRowCount(0);
			System.out.println("3");
//			String temp[] = new String[7];
			
			
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
		            System.out.println("4");
		            model.addRow(data);
/*		            
		            for (int i = 0; i < 7; i++) {
		               System.out.print(data[i] + "\t");
		            }
		            System.out.println();
*/		            
		         }
				
				
				
	} catch (Exception e) { System.out.println("일부 검색 실패");
	
//		finally {
//		try { rs.close(); pstmt.close();con.close(); }
//		catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	
	

	public void updateview(Manager manager) {
		HistoryDto dtoup = new HistoryDto();
		try {
			
//		      if(txtId.getText() != null && !txtId.getText().isEmpty()) {
		      if(manager.txtId.getText().length() == 0) {
		    	  JOptionPane.showMessageDialog(manager, "수정하고 싶은 데이터를 목록에서 선택해주세요.");
		    	  return;
		      }
		      
		      String sql = "SELECT * FROM history where hid = '" + manager.txtId.getText() + "'";
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      
		       
		       if( rs.next() )
		       {
		    	  
		          dtoup.setNo(rs.getString("hno"));
		          dtoup.setName(rs.getString("hmenu"));
		          dtoup.setId(rs.getString("hid"));
		          dtoup.setOption(rs.getString("hoption"));
		          dtoup.setSize(rs.getString("hsize"));
		          dtoup.setPrice(rs.getString("hprice"));
		          dtoup.setDate(rs.getString("hdate"));
//		          System.out.println(dtoup.getId());

		       }
		       
//			Historydto dtoup = new Historydto();
/*		       if(rs.getString("hmenu") == txtName.getText().trim() && rs.getString("hsize") == txtSize.getText().trim() && 
		    		   rs.getString("hsize") == txtIh.getText().trim() && rs.getString("hprice") == txtPrice.getText().trim()) {
					JOptionPane.showMessageDialog(this, "상품명, 사이즈, 옵션, 가격만 수정할 수 있습니다. 수정할 내용을 입력해주세요");
					return;
				}
*/				
		     
/*		       if(!(dtoup.getNo().trim().equals(txtNo.getText().trim()))) {
			    	JOptionPane.showMessageDialog(this, "상품명, 사이즈, 옵션, 가격만 수정할 수 있습니다. 수정할 내용을 입력해주세요");
			    	System.out.println("1");
			    	txtNo.setText(dtoup.getNo());
			    	System.out.println("2");
			    	return;
			   } if (!(dtoup.getId().trim().equals(txtId.getText().trim()))) {
			    	JOptionPane.showMessageDialog(this, "상품명, 사이즈, 옵션, 가격만 수정할 수 있습니다. 수정할 내용을 입력해주세요");
			    	System.out.println("3");
			    	txtId.setText(dtoup.getId());
			    	System.out.println("4");
			    	return;
			   } if (!(dtoup.getDate().trim().equals(txtDate.getText().trim()))){
			    	JOptionPane.showMessageDialog(this, "상품명, 사이즈, 옵션, 가격만 수정할 수 있습니다. 수정할 내용을 입력해주세요");
			    	txtDate.setText(dtoup.getDate());
			    	return;
			   }
			   			   
			   
		       System.out.println(dtoup.getId().trim());
		       System.out.println(txtId.getText().trim());
		      if(!(dtoup.getId().trim().equals(txtId.getText().trim())) || !(dtoup.getNo().trim().equals(txtNo.getText().trim())) 
		    		  || !(dtoup.getDate().trim().equals(txtDate.getText().trim()))) {
		    	  JOptionPane.showMessageDialog(this, "didi상품명, 사이즈, 옵션, 가격만 수정할 수 있습니다. 수정할 내용을 입력해주세요");
		    	  return;
		      }
*/		    	   
		      if(dtoup.getName().trim().equals(manager.txtName.getText().trim()) && dtoup.getSize().trim().equals(manager.txtSize.getText().trim()) && 
					dtoup.getOption().trim().equals(manager.txtIh.getText().trim())  && dtoup.getPrice().trim().equals(manager.txtPrice.getText().trim())) {
//					System.out.println("if-&&문 실행");
		    	   JOptionPane.showMessageDialog(manager, "상품명, 사이즈, 옵션, 가격만 수정할 수 있습니다. 수정할 내용을 입력해주세요");
		    	   return;
			}
		      
		     
		    		  
			String sqlup = "update history set hmenu = '" + manager.txtName.getText()+ "', hsize = '" + manager.txtSize.getText()
					+ "', hoption = '" + manager.txtIh.getText() + "', hprice = '" + manager.txtPrice.getText() + "' where hid = '" + manager.txtId.getText() + "'";
			pstmt = con.prepareStatement(sqlup);
			pstmt.executeUpdate(); 
		} catch (Exception e) { System.out.println("수정 실패"); 
		} 
		
		JOptionPane.showMessageDialog(manager, "정보가 수정되었습니다.");
		
	}

	
	public void deleteview(Manager manager) {
		
		try {
			
			if(manager.txtId.getText().length() == 0) {
		    	  JOptionPane.showMessageDialog(manager, "삭제하고 싶은 데이터를 목록에서 선택해주세요.");
		    	  return;
		      }
			
			String sql = "delete from history where hid = '" + manager.txtId.getText() + "'";
			pstmt = con.prepareStatement(sql);

			int message = JOptionPane.showConfirmDialog(manager, "정말 삭제하시겠습니까?");
			if (message == JOptionPane.YES_OPTION) {
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(manager, "데이터가 삭제되었습니다.");
			}
			
		} catch (Exception e) { System.out.println("삭제 실패"); 
		} 

	}
	
	
	
	
	
}
