//package customer;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//import javax.swing.JOptionPane;
//import javax.swing.table.DefaultTableModel;
//
//public class Test implements KeyListener {
//	
//	public void keyPressed(KeyEvent e) {
//	}
//	public void keyReleased(KeyEvent e) {
//	}
//
//	public void keyTyped(KeyEvent e) {
//		Object o = e.getSource();
//		if (o == tf3) {
//			try {
//	            String name = tfCustName.getText();
//	            //key = customer.searchName(name);
//	            System.out.println(e.getKeyChar());
//	            CustomerDAO dao = new CustomerDAO();
//	            String kkk = e.getKeyChar() + "";
//	            name =  name + kkk;
//
//	            System.out.println(name);
//	           dao.searchName(model, name);
//	           // String sql = "SELECT * FROM customer where pname like '%"+name+"%'";
//	           //stmt = con.createStatement();
//	           // rs = stmt.executeQuery(sql);
//		  	    
//			}catch (Exception ex) { 
//				JOptionPane.showMessageDialog(null, "검색 실패 : " + ex.getMessage());	}
//		}
//	}
//}