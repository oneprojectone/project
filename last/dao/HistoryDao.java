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
	String title[] = { "NO", "���̵�", "��ǰ��", "������", "�ɼ�", "����", "�ֹ���¥" };
	
	DefaultTableModel model = new DefaultTableModel(data, title); 
	
   
   
	 public	HistoryDao() {
		 try {
			connectDB();
			System.out.println("���� �Ϸ�");
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
   
//  init(HistoryView mg) �޼ҵ� ����  ///////////////////////////////////////////////////
   public void init(HistoryView mg) {
	   	mg.txtNo.setText("");
		mg.txtNo.setEditable(false);
		mg.txtId.setText("");
		mg.txtId.setEditable(false);
		mg.comboBox.setSelectedItem("�������ּ���");
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
// l -> Large ����
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
// l -> Large ����
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
// l -> Large ����
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
	
				
	} catch (Exception e) { System.out.println("��ü �˻� ����"); 
	e.printStackTrace();
	} 
		
		
		return temp;
		
	}

  
   
   
   
   public void CusAllview(DefaultTableModel model, String id) {

		try {
			//Ż���� ȸ���� ������ �� ������ ���� �ʵ��� ����(21.05.19) �����
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
			System.out.println("��ü �˻� ����");

		} finally {
			dbClose();
		}
	}

	

// mouseview ����
   public void mouseview(SelectFrame sfr) {
		
		MouseListener mm = new MouseAdapter(){
	        public void mouseClicked(MouseEvent e){
	        	
	        	sfr.txtId.setEditable(false);
	        	sfr.comboBox.setEditable(false);
	        	sfr.txtDate.setEditable(false);
	        	sfr.txtDate2.setEditable(false);
	        	
	        	
	        	if(e.getSource() == sfr.txtId) {
	        		sfr.txtId.setEditable(true);
	        		sfr.comboBox.setSelectedItem("�������ּ���");
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
	        		sfr.comboBox.setSelectedItem("�������ּ���");
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
// l -> Large ����
										              + "when 'Large' then cprice + 500 " 
										              + "else cprice "  
									                  + "end as hprice " 
										              + "from history, menu where history.hmenu IN (menu.cname) and history.hid like '%" + sfr.txtId.getText() + "%' "
										              + "order by hid";
			System.out.println("���̵�� �˻� ����");
		}
		else if (!(sfr.comboBox.getSelectedItem() == "�������ּ���")) {
			sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice, history.hdate, "  
														+ "case hsize " 
// l -> Large ����
														+ "when 'Large' then cprice + 500 "  
														+ "else cprice "  
														+ "end as hprice "  
														+ "from history, menu where history.hmenu IN (menu.cname) and history.hmenu like '%" +sfr.comboBox.getSelectedItem() + "%' "
														+ "order by hno";
			System.out.println("��ǰ������ �˻� ����");
		}
		else if (!(sfr.txtDate.getText().length() == 0)) {
			sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice, history.hdate, "
					+ "case hsize "
// l -> Large ����
					+ "when 'Large' then cprice + 500 " 
					+ "else cprice "
					+ "end as hprice "
//					+ "from history, menu where history.hmenu IN (menu.cname) and history.hdate like '%" + sfr.txtDate.getText() + "%' "
// where�� ����
					+ "from history, menu where history.hmenu IN (menu.cname) and history.hdate between '" + sfr.txtDate.getText() + "' and '" + sfr.txtDate2.getText() + "'"       
					+ "order by hdate";
			System.out.println("��¥�� �˻� ����");
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
			JOptionPane.showMessageDialog(sfr, "��ġ�ϴ� �����Ͱ� �����ϴ�. �ٽ� �˻����ּ���.");
			allview(model, manager);
			
			}
						
		temp[0]=count;
		temp[1]=total;
		
		return temp;
		
	} catch (Exception e) { System.out.println("�Ϻ� �˻� ����");

	}
	return temp;
}
   

// �˻� ���� ( ȸ�� id��)
public void CusSearchview(DefaultTableModel model, String hmenu, String id  ) {
	      try {
	    	// l -> Large ����
	    	// ���� ����	       
	    	// �˻� ���� ( ȸ�� id��)	  

	        //�˻� Ż���� ȸ���� ������ �� ������ ���� �ʰ� ����(21.05.19) ����� 
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
	             System.out.println(e + "=> ����");
	          } finally {
	             dbClose();
	          } 
	 }     
   
   
   
	 public void menusearchview(DefaultTableModel model, String menuitem) {
			
			try {
				String sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice, history.hdate, "  
						+ "case hsize " 
// l -> Large ����
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
			} catch (Exception e) { System.out.println("�޺��ڽ� �˻� ����"); 
			}

		}		
   
	 public HistoryVO modifyhistory(HistoryVO dto) {

			String sql = "UPDATE history SET " + "hmenu = ?, hsize = ?, hoption = ? " + "WHERE hno = ?";
			try {
				pstmt = con.prepareStatement(sql);

				
				pstmt.setString(1, dto.gethMenu());
				if(dto.gethMenu() == "����ġ������ũ" || dto.gethMenu() == "ũ��ũ����"
				  || dto.gethMenu() == "��Ϻ극��" || dto.gethMenu() == "���ڸ���"
				  || dto.gethMenu() == "���̱�" || dto.gethMenu() == "Ƽ��̼�����ũ"
				  || dto.gethMenu() == "��Ʈ��������ũ" || dto.gethMenu() == "����������Ʈ�κ���") {
					pstmt.setString(2, null);
					pstmt.setString(3, null);
				} 
					else if (dto.gethMenu() == "�ڹ�Ĩ����Ǫġ��" || dto.gethMenu() == "��Ʈ����Ĩ����Ǫġ��"
						|| dto.gethMenu() == "�ݵ���" || dto.gethMenu() == "�ݵ����"
						|| dto.gethMenu() == "�ڸ����̵�" || dto.gethMenu() == "�����̵�"
						|| dto.gethMenu() == "������̵�" || dto.gethMenu() == "������Ʈ������"
						|| dto.gethMenu() == "��纣�����Ʈ������" || dto.gethMenu() == "Ű�����Ʈ������"
						|| dto.gethMenu() == "��ü�ݵ���") {
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
				System.out.println("modifyhistory ����");
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
		    	  JOptionPane.showMessageDialog(manager, "�����ϰ� ���� �����͸� ��Ͽ��� �������ּ���.");
		    	  return;
		      }
		      
		      String sql = "select history.hno,history.hid, history.hmenu, history.hsize,history.hoption, menu.cprice, history.hdate, "
		    		  				+ "case hsize "
// l -> Large ����
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
// m -> Medium ����	    	   
		    	   size = "Medium";
		       }else if(manager.rbtnSize_l.isSelected()) {
// l -> Large ����
		    	   size = "Large";
		       }
		   
		    if(manager.rbtnOption_h.isSelected()) {
// h -> Hot ����
		    	   option = "Hot";
		       }else if(manager.rbtnOption_i.isSelected()) {
// i -> Ice ����
		    	   option = "Ice";
		       }
 // if -else�� ����		    
		    if(dtoup.gethSize() == null && dtoup.gethOption() == null) {
		    	  if(dtoup.gethMenu().trim().equals(manager.comboBox.getSelectedItem())) {
			    	   System.out.println("if-&&�� ����");
			    	   JOptionPane.showMessageDialog(manager, "������ ������ �Է����ּ���");
			    	   return;
		    	  }
		      }
		      else {
		    	  if((dtoup.gethMenu().trim().equals(manager.comboBox.getSelectedItem())) && (dtoup.gethSize().trim().equals(size)) && 
							(dtoup.gethOption().trim().equals(option))) {
				    	   System.out.println("if-&&�� ����");
				    	   JOptionPane.showMessageDialog(manager, "������ ������ �Է����ּ���");
				    	   return;
				   }
		      }
		      
		      String sqlup;
		  if (manager.comboBox.getSelectedItem() == "����ġ������ũ" || manager.comboBox.getSelectedItem() == "ũ��ũ����"
				  || manager.comboBox.getSelectedItem() == "��Ϻ극��" || manager.comboBox.getSelectedItem() == "���ڸ���"
				  || manager.comboBox.getSelectedItem() == "���̱�" || manager.comboBox.getSelectedItem() == "Ƽ��̼�����ũ"
				  || manager.comboBox.getSelectedItem() == "��Ʈ��������ũ" || manager.comboBox.getSelectedItem() == "����������Ʈ�κ���") { 
			  sqlup = "update history set hmenu = '" + manager.comboBox.getSelectedItem()+ "', hsize = " + null
						+ ", hoption = " + null + " where hno = '" + manager.txtNo.getText() + "'";
		  }
		  else if (manager.comboBox.getSelectedItem() == "�ڹ�Ĩ����Ǫġ��" || manager.comboBox.getSelectedItem() == "��Ʈ����Ĩ����Ǫġ��"
					|| manager.comboBox.getSelectedItem() == "�ݵ���" || manager.comboBox.getSelectedItem() == "�ݵ����"
					|| manager.comboBox.getSelectedItem() == "�ڸ����̵�" || manager.comboBox.getSelectedItem() == "�����̵�"
					|| manager.comboBox.getSelectedItem() == "������̵�" || manager.comboBox.getSelectedItem() == "������Ʈ������"
					|| manager.comboBox.getSelectedItem() == "��纣�����Ʈ������" || manager.comboBox.getSelectedItem() == "Ű�����Ʈ������"
					|| manager.comboBox.getSelectedItem() == "��ü�ݵ���") { 
// null -> size ���� ,'' ����	///////////////////////////////////////////////////////////////////////////////////////////////////////////			  
			  sqlup = "update history set hmenu = '" + manager.comboBox.getSelectedItem()+ "', hsize = '" + size
					  + "', hoption = 'Ice' where hno = '" + manager.txtNo.getText() + "'";
			  
		  } else {
			  sqlup = "update history set hmenu = '" + manager.comboBox.getSelectedItem()+ "', hsize = '" + size
					  + "', hoption = '" + option + "' where hno = '" + manager.txtNo.getText() + "'";
		  }
			
			pstmt = con.prepareStatement(sqlup);
			pstmt.executeUpdate(); 
			
		} catch (Exception e) { System.out.println("���� ����"); 
		} 
		
		JOptionPane.showMessageDialog(manager, "������ �����Ǿ����ϴ�.");
		
	}

   
   public void deleteview(DefaultTableModel model, HistoryView manager) {
		
		try {
			
			if(manager.txtId.getText().length() == 0) {
		    	  JOptionPane.showMessageDialog(manager, "�����ϰ� ���� �����͸� ��Ͽ��� �������ּ���.");
		    	  return;
		      }
			
			String sql = "delete from history where hno = '" + manager.txtNo.getText() + "'";
			pstmt = con.prepareStatement(sql);

			int message = JOptionPane.showConfirmDialog(manager, "���� �����Ͻðڽ��ϱ�?");
			if (message == JOptionPane.YES_OPTION) {
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(manager, "�����Ͱ� �����Ǿ����ϴ�.");
			}
			
		} catch (Exception e) { System.out.println("���� ����"); 
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
   
   
