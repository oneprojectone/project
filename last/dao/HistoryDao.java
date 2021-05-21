package dao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dbConnect.Singleton;
import dto.HistoryVO;
import view.HistoryView;
import view.HistoryMyView;
import view.SelectFrame;

public class HistoryDao {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt; 
	ResultSet rs;
	
	int count = 0;
	int total = 0;
	

	HistoryVO dto = new HistoryVO();
	SelectFrame sfrF = new SelectFrame();
	
	
	String data[][] = new String[0][7]; 
	String title[] = { "NO", "아이디", "상품명", "사이즈", "옵션", "가격", "주문날짜" };
	
	DefaultTableModel model = new DefaultTableModel(data, title); 
	
   
   
	 public	HistoryDao() {
		 try {
			connectDB();
			System.out.println("연결 완료");
		}catch(Exception e)
		 {
			e.printStackTrace();
		 }}
	 
		void  connectDB() {
			try {
			con = Singleton.getConnection();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
   
//  init(HistoryView mg) 메소드 수정  ///////////////////////////////////////////////////
   public void init(HistoryView mg) {
	   	mg.txtNo.setText("");
		mg.txtNo.setEditable(false);
		mg.txtId.setText("");
		mg.txtId.setEditable(false);
		mg.comboBox.setSelectedItem("선택해주세요");
		mg.comboBox.setEnabled(false);
		mg.rbtnSize_m.setEnabled(false);
		mg.rbtnSize_l.setEnabled(false);
		mg.rbtnOption_h.setEnabled(false);
		mg.rbtnOption_i.setEnabled(false);
		mg.txtPrice.setText("");
		mg.txtPrice.setEditable(false);
		mg.txtDate.setText("");
		mg.txtDate.setEditable(false);
   }
   
   
   public void init(HistoryMyView or) {
		or.txt1.setText("");
		or.txt2.setText("");
		or.txt3.setText("");
		or.txt4.setText("");
		or.txt5.setText("");
	}
   

	public HistoryVO selectByPk(Integer Num) throws Exception{
		   
	       String sql = "select menu.cno, history.hno, history.hid, history.hmenu, history.hsize, history.hoption, menu.cprice, history.hdate, "
	    		   			+ "case hsize " 
// l -> Large 수정
	    		   			+ "when 'Large' then cprice + 500 " 
	    		   			+ "else cprice "
	    		   			+ "end as hprice "
	    		   			+ "from history, menu where history.hmenu IN (menu.cname) and history.hno = " + Num;
	    	
	      
	       stmt = con.createStatement();
	       rs = stmt.executeQuery(sql);
	       if( rs.next() )
	       {
	          dto.sethNo(rs.getString("hno"));
	          dto.sethMenu(rs.getString("hmenu"));
	          dto.sethID(rs.getString("hid"));
	          
	          String option = rs.getString("hoption");
	          
	          if (option == null) { 
	        	  dto.sethOption(null);

	          }
	          else {
	        	  dto.sethOption(rs.getString("hoption"));
	          }
	          
	          String size = rs.getString("hsize");
	          if(size == null) {
	        	  dto.sethSize(null);
	          }
	          else {
	        	  dto.sethSize(rs.getString("hsize"));
	          }
	          
	          dto.sethPrice(rs.getString("hprice"));
	          dto.sethDate(rs.getString("hdate"));

	       }
	       
	       System.out.println("3" + dto.gethOption());
	       System.out.println("4" + dto.gethMenu());
	       rs.close();
	       stmt.close();
	       
	       return dto;
	    }
   
	public HistoryVO selectByMyPk(int Num) throws Exception {

		String sql = "select menu.cno, history.hno, history.hid, history.hmenu, history.hsize, history.hoption, menu.cprice, history.hdate, "
				+ "case hsize "
// l -> Large 수정
				+ "when 'Large' then cprice + 500 " + "else cprice " + "end as hprice "
				+ "from history, menu where history.hmenu IN (menu.cname) and history.hno = " + Num;
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		if (rs.next()) {
			dto.sethNo(rs.getString("hno"));
			dto.sethMenu(rs.getString("hmenu"));
			dto.sethID(rs.getString("hid"));
			dto.sethOption(rs.getString("hoption"));
			dto.sethSize(rs.getString("hsize"));
			dto.sethPrice(rs.getString("hprice"));
			dto.sethDate(rs.getString("hdate"));

		}

		rs.close();
		stmt.close();

		return dto;
	}

	
   public int[] allview(DefaultTableModel model, HistoryView manager) {
		int temp[]=new int[2];
		total=0;
		count=0;
		init(manager);
		
		try {
			String sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice ,history.hdate, " 
					              + "case hsize " 
// l -> Large 수정
					              + "when 'Large' then cprice + 500 "  
					              + "else cprice " 
					              + "end as hprice " 
					              + "from history, menu where history.hmenu IN (menu.cname) "
					              + "order by hno";

			
			pstmt=con.prepareStatement(sql);
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
		                    rs.getString("hdate")};
		                    count++;
		                    total += Integer.parseInt(rs.getString("hprice"));
							
		                    model.addRow(data);
		               };

	 
		     temp[0]=count;
		     temp[1]=total;
		     return temp;
	
				
	} catch (Exception e) { System.out.println("전체 검색 실패"); 
	e.printStackTrace();
	} 
		
		
		return temp;
		
	}

  
   
   
   
   public void CusAllview(DefaultTableModel model, String id) {

		try {
			//탈퇴한 회원이 내역이 내 내역에 뜨지 않도록 수정(21.05.19) 김수정
			String sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice ,history.hdate, " 
		+"case hsize " 
		+"when 'Large' then cprice +500 "
		+"else cprice " 
		+"end as hprice " 
		+"from history, menu "
		+"where history.hdate >= (select pdate from customer where pid = ?) "
		+"and history.hmenu in (menu.cname) "
		+"and hid = ?"
		+"order by hno desc";
			pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, id);
			 pstmt.setString(2, id);
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			

			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			while (rs.next()) {
				 dto.sethNo(rs.getString("hno"));
	             dto.sethMenu(rs.getString("hmenu"));
	             dto.sethID(rs.getString("hid"));
	             dto.sethOption(rs.getString("hoption"));
	             dto.sethSize(rs.getString("hsize"));
	             dto.sethPrice(rs.getString("hprice"));
	             dto.sethDate(rs.getString("hdate"));
				Object[] data = { dto.gethNo(), dto.gethID(), dto.gethMenu(), dto.gethSize(), dto.gethOption(),
						dto.gethPrice(), dto.gethDate() };
				model.addRow(data);
			}

		} catch (Exception e) {
			System.out.println("전체 검색 실패");

		} finally {
			dbClose();
		}
	}

	

// mouseview 수정
   public void mouseview(SelectFrame sfr) {
		
		MouseListener mm = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	
	        	sfr.txtId.setEditable(false);
	        	sfr.comboBox.setEditable(false);
	        	sfr.txtDate.setEditable(false);
	        	sfr.txtDate2.setEditable(false);
	        	
	        	
	        	if(e.getSource() == sfr.txtId) {
	        		sfr.txtId.setEditable(true);
	        		sfr.comboBox.setSelectedItem("선택해주세요");
	        		sfr.comboBox.setEnabled(false);
	        		sfr.txtDate.setText("");
	        		sfr.txtDate2.setText("");
	        		
	        	} else if(e.getSource() == sfr.comboBox) {
	        		sfr.txtId.setText("");
	        		sfr.comboBox.setEnabled(true);
	        		sfr.txtDate.setText("");
	        		sfr.txtDate2.setText("");
	        		
	        	} else if(e.getSource() == sfr.txtDate) {
	        		sfr.txtId.setText("");
	        		sfr.comboBox.setSelectedItem("선택해주세요");
	        		sfr.comboBox.setEnabled(false);
	        		sfr.txtDate.setEditable(true);
	        		sfr.txtDate2.setEditable(true);

	        	}
	        }
	   };
	   
	   sfr.txtId.addMouseListener(mm);
	   sfr.comboBox.addMouseListener(mm);
	   sfr.txtDate.addMouseListener(mm);
		
	}
   
   
public int[] searchview(DefaultTableModel model, SelectFrame sfr, HistoryView manager) {
	
	String sql = "";
	int temp[]=new int[2];
	total=0;
	count=0;

	try {
		if (!(sfr.txtId.getText().length() == 0)) {
			sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice ,history.hdate, "  
										              + "case hsize " 
// l -> Large 수정
										              + "when 'Large' then cprice + 500 " 
										              + "else cprice "  
									                  + "end as hprice " 
										              + "from history, menu where history.hmenu IN (menu.cname) and history.hid like '%" + sfr.txtId.getText() + "%' "
										              + "order by hid";
			System.out.println("아이디로 검색 실행");
		}
		else if (!(sfr.comboBox.getSelectedItem() == "선택해주세요")) {
			sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice, history.hdate, "  
														+ "case hsize " 
// l -> Large 수정
														+ "when 'Large' then cprice + 500 "  
														+ "else cprice "  
														+ "end as hprice "  
														+ "from history, menu where history.hmenu IN (menu.cname) and history.hmenu like '%" +sfr.comboBox.getSelectedItem() + "%' "
														+ "order by hno";
			System.out.println("상품명으로 검색 실행");
		}
		else if (!(sfr.txtDate.getText().length() == 0)) {
			sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice, history.hdate, "
					+ "case hsize "
// l -> Large 수정
					+ "when 'Large' then cprice + 500 " 
					+ "else cprice "
					+ "end as hprice "
//					+ "from history, menu where history.hmenu IN (menu.cname) and history.hdate like '%" + sfr.txtDate.getText() + "%' "
// where절 수정
					+ "from history, menu where history.hmenu IN (menu.cname) and history.hdate between '" + sfr.txtDate.getText() + "' and '" + sfr.txtDate2.getText() + "'"       
					+ "order by hdate";
			System.out.println("날짜로 검색 실행");
		}
		
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(); 
		model.setRowCount(0);
		String datas[] = new String[7];

		int sum = 0;
		while (rs.next()) {
			datas[0] = rs.getString("hno");
			datas[1] =rs.getString("hid");
			datas[2] =rs.getString("hmenu");
			datas[3] =rs.getString("hsize");
			datas[4] =rs.getString("hoption");
			datas[5] =rs.getString("hprice");
			datas[6] =rs.getString("hdate");
		
				model.addRow(datas);
				count++;
				total+=Integer.parseInt(datas[5]);
				} 
		
		if(count == 0) {
			JOptionPane.showMessageDialog(sfr, "일치하는 데이터가 없습니다. 다시 검색해주세요.");
			allview(model, manager);
			
			}
						
		temp[0]=count;
		temp[1]=total;
		
		return temp;
		
	} catch (Exception e) { System.out.println("일부 검색 실패");

	}
	return temp;
}
   

// 검색 수정 ( 회원 id만)
public void CusSearchview(DefaultTableModel model, String hmenu, String id  ) {
	      try {
	    	// l -> Large 수정
	    	// 가격 수정	       
	    	// 검색 수정 ( 회원 id만)	  

	        //검색 탈퇴한 회원의 내역이 내 내역에 뜨지 않게 수정(21.05.19) 김수정 
	     	String sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice ,history.hdate, " 
	     			+"case hsize " 
	     			+"when 'Large' then cprice +500 "
	     			+"else cprice " 
	     			+"end as hprice " 
	     			+"from history, menu "
	     			+"where history.hdate >= (select pdate from customer where pid = ?) "
	     			+"and history.hmenu in (menu.cname) and hmenu like '%" + hmenu.trim() + "%'"
	     			+"and hid = ?"
	     			+ "order by hno desc" ;
	     				pstmt = con.prepareStatement(sql);
	     				 pstmt.setString(1, id);
	     				 pstmt.setString(2, id);
	         
	         
	         rs = pstmt.executeQuery();
	         
	         for (int i = 0; i < model.getRowCount();) {
	             model.removeRow(0);
	         }
	             while (rs.next()) {
	            	 dto.sethNo(rs.getString("hno"));
		             dto.sethMenu(rs.getString("hmenu"));
		             dto.sethID(rs.getString("hid"));
		             dto.sethOption(rs.getString("hoption"));
		             dto.sethSize(rs.getString("hsize"));
		             dto.sethPrice(rs.getString("hprice"));
		             dto.sethDate(rs.getString("hdate"));
					Object[] data = { dto.gethNo(), dto.gethID(), dto.gethMenu(), dto.gethSize(), dto.gethOption(),
							dto.gethPrice(), dto.gethDate() };
	 				model.addRow(data);
	 			}     
	         } catch (SQLException e) {
	             System.out.println(e + "=> 실패");
	          } finally {
	             dbClose();
	          } 
	 }     
   
   
   
	 public void menusearchview(DefaultTableModel model, String menuitem) {
			
			try {
				String sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice, history.hdate, "  
						+ "case hsize " 
// l -> Large 수정
						+ "when 'Large' then cprice + 500 "  
						+ "else cprice "  
						+ "end as hprice "  
						+ "from history, menu where history.hmenu IN (menu.cname) and history.hmenu = '" + menuitem + "'";
			
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

		     }
			} catch (Exception e) { System.out.println("콤보박스 검색 실패"); 
			}

		}		
   
	 public HistoryVO modifyhistory(HistoryVO dto) {

			String sql = "UPDATE history SET " + "hmenu = ?, hsize = ?, hoption = ? " + "WHERE hno = ?";
			try {
				pstmt = con.prepareStatement(sql);

				
				pstmt.setString(1, dto.gethMenu());
				if(dto.gethMenu() == "뉴욕치즈케이크" || dto.gethMenu() == "크로크무슈"
				  || dto.gethMenu() == "허니브레드" || dto.gethMenu() == "초코머핀"
				  || dto.gethMenu() == "베이글" || dto.gethMenu() == "티라미수케이크"
				  || dto.gethMenu() == "민트초코케이크" || dto.gethMenu() == "베리베리스트로베리") {
					pstmt.setString(2, null);
					pstmt.setString(3, null);
				} 
					else if (dto.gethMenu() == "자바칩프라푸치노" || dto.gethMenu() == "민트초코칩프라푸치노"
						|| dto.gethMenu() == "콜드브루" || dto.gethMenu() == "콜드브루라떼"
						|| dto.gethMenu() == "자몽에이드" || dto.gethMenu() == "레몬에이드"
						|| dto.gethMenu() == "사과에이드" || dto.gethMenu() == "딸기요거트스무디"
						|| dto.gethMenu() == "블루베리요거트스무디" || dto.gethMenu() == "키위요거트스무디"
						|| dto.gethMenu() == "돌체콜드브루") {
					pstmt.setString(2, dto.gethSize());
					pstmt.setString(3, "Ice");
				}
				else {
				pstmt.setString(2, dto.gethSize());
				pstmt.setString(3, dto.gethOption());
				}
				pstmt.setString(4, dto.gethNo());
				pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println("modifyhistory 실패");
			} finally {
				try {
					pstmt.close();
				} catch (Exception e) {
				}

			}

			return dto;
		}

   
   

   public void updateview(DefaultTableModel model,HistoryView manager) {
	   HistoryVO dtoup = new HistoryVO();
		String size = "";
		String option = "";
		int count = 0;
		try {
		      if(manager.txtId.getText().length() == 0) {
		    	  JOptionPane.showMessageDialog(manager, "수정하고 싶은 데이터를 목록에서 선택해주세요.");
		    	  return;
		      }
		      
		      String sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice, history.hdate, "
		    		  				+ "case hsize "
// l -> Large 수정
		    		  				+ "when 'Large' then cprice + 500 " 
		    		  				+ "else cprice "
		    		  				+ "end as hprice "
		    		  				+ "from history, menu where history.hmenu IN (menu.cname) and history.hno = '"+ manager.txtNo.getText() + "'";
		    		
		      Statement stmt = con.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      
		       if( rs.next() )
		       {
		          dtoup.sethNo(rs.getString("hno"));
		          dtoup.sethMenu(rs.getString("hmenu"));
		          dtoup.sethID(rs.getString("hid"));
		          
		          String optionup = rs.getString("hoption");
		          if (optionup == null) { 
		        	  dtoup.sethOption(null);
		          }
		          else {
		        	  dtoup.sethOption(rs.getString("hoption"));
		          }
		          
		          String sizeup = rs.getString("hsize");
		          if(sizeup == null) {
		        	  dtoup.sethSize(null);
		          }
		          else {
		        	  dtoup.sethSize(rs.getString("hsize"));
		          }
		          dtoup.sethDate(rs.getString("hdate"));

		       }
		       if(manager.rbtnSize_m.isSelected()) {
// m -> Medium 수정	    	   
		    	   size = "Medium";
		       }else if(manager.rbtnSize_l.isSelected()) {
// l -> Large 수정
		    	   size = "Large";
		       }
		   
		    if(manager.rbtnOption_h.isSelected()) {
// h -> Hot 수정
		    	   option = "Hot";
		       }else if(manager.rbtnOption_i.isSelected()) {
// i -> Ice 수정
		    	   option = "Ice";
		       }
 // if -else문 수정		    
		    if(dtoup.gethSize() == null && dtoup.gethOption() == null) {
		    	  if(dtoup.gethMenu().trim().equals(manager.comboBox.getSelectedItem())) {
			    	   System.out.println("if-&&문 실행");
			    	   JOptionPane.showMessageDialog(manager, "수정할 내용을 입력해주세요");
			    	   return;
		    	  }
		      }
		      else {
		    	  if((dtoup.gethMenu().trim().equals(manager.comboBox.getSelectedItem())) && (dtoup.gethSize().trim().equals(size)) && 
							(dtoup.gethOption().trim().equals(option))) {
				    	   System.out.println("if-&&문 실행");
				    	   JOptionPane.showMessageDialog(manager, "수정할 내용을 입력해주세요");
				    	   return;
				   }
		      }
		      
		      String sqlup;
		  if (manager.comboBox.getSelectedItem() == "뉴욕치즈케이크" || manager.comboBox.getSelectedItem() == "크로크무슈"
				  || manager.comboBox.getSelectedItem() == "허니브레드" || manager.comboBox.getSelectedItem() == "초코머핀"
				  || manager.comboBox.getSelectedItem() == "베이글" || manager.comboBox.getSelectedItem() == "티라미수케이크"
				  || manager.comboBox.getSelectedItem() == "민트초코케이크" || manager.comboBox.getSelectedItem() == "베리베리스트로베리") { 
			  sqlup = "update history set hmenu = '" + manager.comboBox.getSelectedItem()+ "', hsize = " + null
						+ ", hoption = " + null + " where hno = '" + manager.txtNo.getText() + "'";
		  }
		  else if (manager.comboBox.getSelectedItem() == "자바칩프라푸치노" || manager.comboBox.getSelectedItem() == "민트초코칩프라푸치노"
					|| manager.comboBox.getSelectedItem() == "콜드브루" || manager.comboBox.getSelectedItem() == "콜드브루라떼"
					|| manager.comboBox.getSelectedItem() == "자몽에이드" || manager.comboBox.getSelectedItem() == "레몬에이드"
					|| manager.comboBox.getSelectedItem() == "사과에이드" || manager.comboBox.getSelectedItem() == "딸기요거트스무디"
					|| manager.comboBox.getSelectedItem() == "블루베리요거트스무디" || manager.comboBox.getSelectedItem() == "키위요거트스무디"
					|| manager.comboBox.getSelectedItem() == "돌체콜드브루") { 
// null -> size 수정 ,'' 넣음	///////////////////////////////////////////////////////////////////////////////////////////////////////////			  
			  sqlup = "update history set hmenu = '" + manager.comboBox.getSelectedItem()+ "', hsize = '" + size
					  + "', hoption = 'Ice' where hno = '" + manager.txtNo.getText() + "'";
			  
		  } else {
			  sqlup = "update history set hmenu = '" + manager.comboBox.getSelectedItem()+ "', hsize = '" + size
					  + "', hoption = '" + option + "' where hno = '" + manager.txtNo.getText() + "'";
		  }
			
			pstmt = con.prepareStatement(sqlup);
			pstmt.executeUpdate(); 
			
		} catch (Exception e) { System.out.println("수정 실패"); 
		} 
		
		JOptionPane.showMessageDialog(manager, "정보가 수정되었습니다.");
		
	}

   
   public void deleteview(DefaultTableModel model, HistoryView manager) {
		
		try {
			
			if(manager.txtId.getText().length() == 0) {
		    	  JOptionPane.showMessageDialog(manager, "삭제하고 싶은 데이터를 목록에서 선택해주세요.");
		    	  return;
		      }
			
			String sql = "delete from history where hno = '" + manager.txtNo.getText() + "'";
			pstmt = con.prepareStatement(sql);

			int message = JOptionPane.showConfirmDialog(manager, "정말 삭제하시겠습니까?");
			if (message == JOptionPane.YES_OPTION) {
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(manager, "데이터가 삭제되었습니다.");
			}
			
		} catch (Exception e) { System.out.println("삭제 실패"); 
		} 

	}
   
   public void deleteMyInfo(int data) {
		try {
			String sql = "DELETE FROM history WHERE hno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, data);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
   
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}

	public void dbConnect() {
		// TODO Auto-generated method stub
		
	}

}   
   
   
