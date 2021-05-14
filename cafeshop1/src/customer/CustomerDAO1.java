package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerDAO1 {
	Connection con; 	PreparedStatement pstmt; 	
	ResultSet rs; 		Statement stmt;
	String sql;
	JTable		tableCustomer;
	
	public	CustomerDAO1() {
		try {
		connectDB();
		System.out.println("연결 완료");
	} catch(Exception e) { e.printStackTrace(); } 
	}
	
	void connectDB() {
		try {
			con = Singleton.getConnection();
	} catch (Exception e) { e.printStackTrace();}
	}
	
	public void ListAll(DefaultTableModel model) {
 
		String sql = "SELECT * FROM customer";
		String temp[] = new String[9];
		
		tableCustomer = new JTable(model);
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
		while (rs.next()) {
			temp[0] = rs.getString("pname");
			temp[1] = rs.getString("pid");
			temp[2] = rs.getString("ppwd");
			temp[3] = rs.getString("pgender");
			temp[4] = rs.getString("pemail");
			temp[5] = rs.getString("ptel");
			temp[6] = rs.getString("paddr");
			temp[7] = rs.getString("paccount");
			temp[8] = rs.getString("pdate");
			model.addRow(temp);
		}
		} catch(Exception e) {
		e.printStackTrace();
	}
		}
	public DefaultTableModel searchName(DefaultTableModel model, String name) throws Exception{
		
	   String sql = "SELECT * FROM customer where pname like '%"+name+"%'";
         stmt = con.createStatement();
         rs = stmt.executeQuery(sql);
         CustomerVO vo = new CustomerVO();
         String data[][] = new String [0][9];
     	String title[] = { "이름", "ID", "비밀번호", "성별", "EMAIL", "전화번호", "주소", "계좌번호", "가입날짜"};
     	for (int i = 0; i < model.getRowCount();) {
            model.removeRow(0);
         }
     	
     	String temp[] = new String[9];
         tableCustomer = new JTable(model);
         while(rs.next()) {
        	 temp[0] = rs.getString("pname");
 			temp[1] = rs.getString("pid");
 			temp[2] = rs.getString("ppwd");
 			temp[3] = rs.getString("pgender");
 			temp[4] = rs.getString("pemail");
 			temp[5] = rs.getString("ptel");
 			temp[6] = rs.getString("paddr");
 			temp[7] = rs.getString("paccount");
 			temp[8] = rs.getString("pdate");
 			model.addRow(temp);
         }
         rs.close();
         stmt.close();
         return model;
	}	
	
	public void modifyCustomer(CustomerVO cust) throws Exception{
	
		String sql = "UPDATE customer SET pname = ?, "
				+ "pgender = ?, pemail = ?, ptel = ?, paddr = ? "
				+ "WHERE pid = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cust.getPname());
		ps.setString(2, cust.getPgender());
		ps.setString(3, cust.getPemail());
		ps.setString(4, cust.getPtel());
		ps.setString(5, cust.getPaddr());
		ps.setString(6, cust.getPid());
		ps.executeUpdate();
		ps.close();
	}
	
	public void deleteCustomer(String name) throws Exception{
		String sql = "delete from customer where pname = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.executeUpdate();
		pstmt.close();
		
	}
	public CustomerVO selectByPk(String id) throws Exception{
		CustomerVO vo = new CustomerVO();
		
		String sql = "SELECT * FROM customer where pid = '"+id+"'";
		
		Statement stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		if( rs.next() )
		{
			vo.setPname(rs.getString("PNAME")); 
            vo.setPid(rs.getString("PID")); 
            vo.setPpwd(rs.getString("PPWD")); 
            vo.setPgender(rs.getString("PGENDER")); 
            vo.setPemail(rs.getString("PEMAIL")); 
            vo.setPtel(rs.getString("PTEL"));
            vo.setPaddr(rs.getString("PADDR"));
            vo.setPaccount(rs.getString("PACCOUNT"));
            vo.setPdate(rs.getString("PDATE"));
		}
		
		rs.close();
		stmt.close();
		
		return vo;
	}
	public int findMyPwd(String id,String tel) {
		 String password=null;
		 int count=0;
		 try {
			 sql="SELECT PPWD FROM CUSTOMER WHERE PID = ? AND PTEL = ?";
			 pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, tel);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					password=rs.getString("PPWD");
					JOptionPane.showMessageDialog(null, "인증되었습니다!\n"+"회원님의 비밀번호는 "+password+"입니다.");
					count++;
					return count;
				}
				else {
					JOptionPane.showMessageDialog(null, "없는 아이디이거나 전화번호가 일치하지 않습니다!\n"+"아이디와 전화번호를 확인해주시거나 회원가입해주세요.");
					return count;
				}
		}catch(Exception e) {
					 e.printStackTrace();
		}
		 return count;
	}
	public int findMyId(String name,String tel) {
		 String ID=null;
		 int count=0;
		 try {
			 sql="SELECT PID FROM CUSTOMER WHERE Pname = ? AND PTEL = ?";
			 pstmt=con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, tel);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					ID=rs.getString("PID");
					JOptionPane.showMessageDialog(null, "인증되었습니다!\n"+"회원님의 ID는 "+ID+"입니다.");
					count++;
					return count;
				}
				else {
					JOptionPane.showMessageDialog(null, "일치하는 ID가 없습니다.\n"+"이름과 전화번호를 확인해주시거나 회원가입해주세요.");
					return count;
				}
		}catch(Exception e) {
					 e.printStackTrace();
		}
		 return count;
	}
}